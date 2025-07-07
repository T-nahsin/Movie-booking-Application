package com.tnahsin.bookMovies.service;


import com.tnahsin.bookMovies.dtos.SeatStatus;
import com.tnahsin.bookMovies.dtos.ShowTimeResponse;
import com.tnahsin.bookMovies.entity.*;
import com.tnahsin.bookMovies.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShowService {

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;


    public String getLoggedInUserCity() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username);
        return user.getCity();
    }




    public void saveShow(Show show, String screenId) {
        Screen screen = screenRepository.findById(screenId).get();
        show.setScreenId(screenId);
        show.setTheaterId(screen.getTheaterId());
        Show saved = showRepository.save(show);
        screen.getShows().add(saved);
        screenRepository.save(screen);

        List<Seats>seats = seatRepository.findByScreenId(screenId);
        List<ShowSeats> showSeats = new ArrayList<>();
        for(Seats seat : seats) {
            ShowSeats ss = new ShowSeats();
            ss.setShowId(show.getId());
            ss.setSeatId(seat.getId());
            ss.setStatus(SeatStatus.AVAILABLE);
            showSeats.add(ss);
        }
        saved.setShowSeats(showSeats);
        showSeatsRepository.saveAll(showSeats);
    }

    public List<ShowTimeResponse> getShowTimes(String movieId) {
        List<ShowTimeResponse> showTimeResponses = new ArrayList<>();

        String userCity = getLoggedInUserCity();
        List<ObjectId>showsId = movieRepository.findById(movieId).get().getShows();
        List<Show>allShows = new ArrayList<>();

        for(ObjectId s1 :showsId) {
            allShows.add(showRepository.findById(s1).get());
        }
        for(Show show : allShows) {
            Theater theater = theaterRepository.findById(show.getTheaterId()).get();
            if(theater.getCity().equals(userCity)) {
                String screen = screenRepository.findById(show.getScreenId()).get().getScreenName();
                ShowTimeResponse showTimeResponse = new ShowTimeResponse(theater.getName(),
                        screen,
                        movieRepository.findById(movieId).get().getTitle(),
                        show.getShowTime());
                showTimeResponses.add(showTimeResponse);
            }
        }
        if(showTimeResponses.isEmpty()) {
            return null;
        }
        return showTimeResponses;
    }

    public List<ShowTimeResponse> getShowTimes(String movieId, ObjectId theaterId) {
        List<ShowTimeResponse> showTimeResponses = new ArrayList<>();

        Movie movie = movieRepository.findById(movieId).get();
        List<ObjectId>showsId = movie.getShows();
        List<Show> allShows = new ArrayList<>();
        for(ObjectId show : showsId) {
            allShows.add(showRepository.findById(show).get());
        }
        Theater theater = theaterRepository.findById(theaterId).get();
        for (Show show : allShows) {
            if (theaterId.equals(show.getTheaterId())){
                String screen = screenRepository.findById(show.getScreenId()).get().getScreenName();
                ShowTimeResponse showTimeResponse = new ShowTimeResponse(theater.getName(),
                        screen,
                        movieRepository.findById(movieId).get().getTitle(),
                        show.getShowTime());

                showTimeResponses.add(showTimeResponse);
            }
        }
        if(showTimeResponses.isEmpty()) return null;
        return showTimeResponses;
    }
}
