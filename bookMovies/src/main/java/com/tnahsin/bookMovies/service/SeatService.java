package com.tnahsin.bookMovies.service;

import com.tnahsin.bookMovies.dtos.SeatStatus;
import com.tnahsin.bookMovies.entity.Screen;
import com.tnahsin.bookMovies.entity.Seats;
import com.tnahsin.bookMovies.entity.Show;
import com.tnahsin.bookMovies.entity.ShowSeats;
import com.tnahsin.bookMovies.repository.ScreenRepository;
import com.tnahsin.bookMovies.repository.SeatRepository;
import com.tnahsin.bookMovies.repository.ShowSeatsRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    ScreenRepository screenRepository;

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

    public String bookSeats(ObjectId showId , List<String> seatIds , ObjectId userId) {
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
            seat.setStatus(SeatStatus.BLOCKED);
            seat.setBlockedAt(LocalDateTime.now());
            seat.setBlockedByUserId(userId);
        }

        showSeatsRepository.saveAll(seats);
        return "Seats are booked for user " + userId;
    }


    public String blockSeats(ObjectId showId, List<String> seatIds, ObjectId userId) {
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

    public String cancelBooking(ObjectId showId , List<String> seatIds , ObjectId userId) {
        try {
            List<ShowSeats> seats = showSeatsRepository.findByShowIdAndSeatIdIn(showId, seatIds);
            for (ShowSeats showSeat : seats) {
                if (showSeat.getStatus() != SeatStatus.BOOKED) {
                    throw new RuntimeException("Seat " + showSeat.getSeatId()+ " is not booked.");
                }
                showSeat.setStatus(SeatStatus.AVAILABLE);
                showSeat.setBlockedAt(null);
                showSeat.setBlockedByUserId(null);
            }
            return "Cancellation successful";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
