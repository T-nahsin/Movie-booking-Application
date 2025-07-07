package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.dtos.ShowTimeResponse;
import com.tnahsin.bookMovies.entity.Show;
import com.tnahsin.bookMovies.entity.ShowSeats;
import com.tnahsin.bookMovies.repository.ShowRepository;
import com.tnahsin.bookMovies.service.SeatService;
import com.tnahsin.bookMovies.service.ShowService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RequestMapping("/user")
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    ShowService showService;

    @Autowired
    ShowRepository showRepository;


    @Autowired
    private SeatService seatService;


    @PostMapping("/check-seats-available")
    public ResponseEntity<?> seatsAvl(@RequestParam ObjectId showId) {
        try {
            List<ShowSeats> showSeats = seatService.avlSeats(showId);
            List<String>ids = new ArrayList<>();
            for(ShowSeats showSeats1:showSeats){
                ids.add(showSeats1.getSeatId());
            }
            return new ResponseEntity<>(ids,HttpStatus.FOUND);
        } catch (Exception e) {
            log.warn("recheck available seats");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/book-seats")
    public ResponseEntity<?> bookSeats(
            @RequestParam ObjectId showId,
            @RequestParam ObjectId userId,
            @RequestBody List<String> seatIds) {

        try {
            String message = seatService.bookSeats(showId, seatIds, userId);
            return new ResponseEntity<>(message,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


    @PostMapping("/block-seats")
    public ResponseEntity<?> blockSeats(
            @RequestParam ObjectId showId,
            @RequestParam ObjectId userId,
            @RequestBody List<String> seatIds) {

        try {
            String message = seatService.blockSeats(showId, seatIds, userId);
            return new ResponseEntity<>(message,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


    @PostMapping("/cancel-booking")
    public ResponseEntity<?> cancelBooking(
            @RequestParam ObjectId showId,
            @RequestParam ObjectId userId,
            @RequestBody List<String> seatIds) {

        try {
            String message = seatService.cancelBooking(showId, seatIds, userId);
            return new ResponseEntity<>(message,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


    @GetMapping("/get-showtime")
    public ResponseEntity<?> getShowTimes(
            @RequestParam String movieId,
            @RequestParam(required = false) ObjectId theaterId) {

        List<ShowTimeResponse> showTimes=new ArrayList<>();
        if(theaterId != null){
        showTimes = showService.getShowTimes(movieId,theaterId);
        }else {
            showTimes = showService.getShowTimes(movieId);
        }
        if (showTimes  == null) {
            return new ResponseEntity<>("Movie is not avl in your city",HttpStatus.FOUND);
        }
        return new ResponseEntity<>(showTimes,HttpStatus.FOUND);
    }
}
