package com.tnahsin.bookMovies.service;

import com.tnahsin.bookMovies.dtos.BookingDto;
import com.tnahsin.bookMovies.dtos.SeatStatus;
import com.tnahsin.bookMovies.entity.*;
import com.tnahsin.bookMovies.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class SeatService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;



    public void saveSeats(List<Seats>seats , String screenId) {
        Screen screen = screenRepository.findById(screenId).get();
        for(Seats seat : seats) {
            seat.setScreenId(screenId);
            seat = seatRepository.save(seat);
            screen.getSeats().add(seat);
            screenRepository.save(screen);
        }
    }

    public List<ShowSeats> avlSeats(ObjectId showId){
        List<ShowSeats> showSeats = showSeatsRepository.findByShowId(showId);
        List<ShowSeats> seatsList = new ArrayList<>();
        for(ShowSeats seats : showSeats) {
            if(seats.getStatus() == SeatStatus.AVAILABLE)
                seatsList.add(seats);
        }
        return seatsList;
    }

    public String bookSeats(BookingDto bookingDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = userRepository.findByUserName(username).getEmail();

        ObjectId showId = bookingDto.getShowId();
        ObjectId  userId = bookingDto.getUserId();
        List<String> seatIds = bookingDto.getSeatIds();
        List<ShowSeats> seats = showSeatsRepository.findByShowIdAndSeatIdIn(showId , seatIds);


        for (ShowSeats seat : seats) {
            if (seat.getStatus() == SeatStatus.BOOKED) {
                log.error("Seats cannot be booked");
            }
            if (seat.getStatus() == SeatStatus.BLOCKED &&
                    seat.getBlockedAt() != null &&
                    seat.getBlockedAt().isAfter(LocalDateTime.now().minusMinutes(5))) {
                throw new RuntimeException("Seat " + seat.getId() + " is temporarily blocked.");
            }
        }

        for (ShowSeats seat : seats) {
            seat.setStatus(SeatStatus.BOOKED);
            seat.setBlockedAt(LocalDateTime.now());
            seat.setBlockedByUserId(userId);
        }

        Show show = showRepository.findById(showId).get();
        Movie movie  = movieRepository.findById(show.getMovieId()).get();

        // Send welcome email
        String subject = "Booking Successful!";
        String message = "Hi " + username + ",\n\nThank you for booking your movie "+ movie.getTitle()  +" with us.\n\nYour show timing is "+ show.getShowTime()+ "\n\nEnjoy your movie! 🎬";
        emailService.sendMail(email, subject, message);


        showSeatsRepository.saveAll(seats);
        return "Seats are booked for user " + userId;
    }


    public String blockSeats(BookingDto bookingDto) {

        ObjectId showId = bookingDto.getShowId();
        ObjectId  userId = bookingDto.getUserId();
        List<String> seatIds = bookingDto.getSeatIds();
        List<ShowSeats> seats = showSeatsRepository.findByShowIdAndSeatIdIn(showId, seatIds);

        for (ShowSeats seat : seats) {
            if (seat.getStatus() == SeatStatus.BOOKED) {
                log.error("Seats are booked");
                continue;
            }
            if (seat.getStatus() == SeatStatus.BLOCKED &&
                    seat.getBlockedAt() != null &&
                    seat.getBlockedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {

                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setBlockedAt(null);
                seat.setBlockedByUserId(null);
            }
        }

        for (ShowSeats seat : seats) {
            seat.setStatus(SeatStatus.BLOCKED);
            seat.setBlockedAt(LocalDateTime.now());
            seat.setBlockedByUserId(userId);
        }

        showSeatsRepository.saveAll(seats);
        return "Seats temporarily blocked for user " + userId;
    }

    public String cancelBooking(BookingDto bookingDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = userRepository.findByUserName(username).getEmail();

        try {
            ObjectId showId = bookingDto.getShowId();
            ObjectId  userId = bookingDto.getUserId();
            List<String> seatIds = bookingDto.getSeatIds();
            List<ShowSeats> seats = showSeatsRepository.findByShowIdAndSeatIdIn(showId, seatIds);
            for (ShowSeats showSeat : seats) {
                if (showSeat.getStatus() != SeatStatus.BOOKED) {
                    throw new RuntimeException("Seat " + showSeat.getSeatId()+ " is not booked.");
                }
                showSeat.setStatus(SeatStatus.AVAILABLE);
                showSeat.setBlockedAt(null);
                showSeat.setBlockedByUserId(null);
            }

            Show show = showRepository.findById(showId).get();
            Movie movie  = movieRepository.findById(show.getMovieId()).get();

            // Send welcome email
            String subject = "Booking Cancelled!";
            String message = "Hi " + username + ",\n\nYour booking for the movie "+ movie.getTitle()  +" has been cancelled.\n\n Let us know if you have any problem while booking! 🎬";
            emailService.sendMail(email, subject, message);

            return "Cancellation successful";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
