package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.dtos.BookingDto;
import com.tnahsin.bookMovies.dtos.ShowTimeResponse;
import com.tnahsin.bookMovies.entity.ShowSeats;
import com.tnahsin.bookMovies.repository.ShowRepository;
import com.tnahsin.bookMovies.service.SeatService;
import com.tnahsin.bookMovies.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/user")
@RestController
@Tag(name = "User APIs" , description = "All the APIs that user can handle")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    ShowService showService;

    @Autowired
    ShowRepository showRepository;


    @Autowired
    private SeatService seatService;


    @Operation(summary = "Check the seats available in the show")
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


    @Operation(summary = "Book your seats")
    @PostMapping("/book-seats")
    public ResponseEntity<?> bookSeats(
            @RequestBody BookingDto bookingDto) {

        try {
            String message = seatService.bookSeats(bookingDto);
            return new ResponseEntity<>(message,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


    @Operation(summary = "Block the seats for users")
    @PostMapping("/block-seats")
    public ResponseEntity<?> blockSeats(
            @RequestBody BookingDto bookingDto) {

        try {
            String message = seatService.blockSeats(bookingDto);
            return new ResponseEntity<>(message,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


    @Operation(summary = "Cancel your booking")
    @PostMapping("/cancel-booking")
    public ResponseEntity<?> cancelBooking(
            @RequestBody BookingDto bookingDto
            ) {

        try {
            String message = seatService.cancelBooking(bookingDto);
            return new ResponseEntity<>(message,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


    @Operation(summary = "Get the showtime of of your movie ")
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
