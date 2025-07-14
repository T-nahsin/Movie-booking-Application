package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.dtos.*;
import com.tnahsin.bookMovies.entity.*;
import com.tnahsin.bookMovies.repository.MovieRepository;
import com.tnahsin.bookMovies.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/admin")
@RestController
@Slf4j
@PreAuthorize("hasRole('admin')")
@Tag(name = "Admin APIs" , description = "All the APIs that user handles")
public class AdminController {

    @Autowired
    MovieRepository movieRepository;


    @Autowired
    MovieService movieService;

    @Autowired
    TheaterService theaterService;

    @Autowired
    ScreenService screenService;


    @Autowired
    ShowService showService;

    @Autowired
    SeatService seatService;

    @Operation(summary = "Add a new movie to the system")
    @PostMapping("/add-movie")
    public ResponseEntity<?> addMovie(@RequestBody MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setLanguage(movieDto.getLanguage());
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());
        try {
            movieRepository.save(movie);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured in saving movie{}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Add a new showtime to the screen")
    @PostMapping("/add-showtime")
    public ResponseEntity<?> addShowTime(@RequestBody MovieDto movieDto, @RequestParam ObjectId showId) {
        try {
            Movie movie = new Movie();
            movie.setId(movieDto.getId());
            movie.setTitle(movieDto.getTitle());
            movie.setLanguage(movieDto.getLanguage());
            movie.setGenre(movieDto.getGenre());
            movie.setDescription(movieDto.getDescription());
            movieService.addShowTime(movie, showId);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured in add shows{}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Add a new movie and its show")
    @PostMapping("/save-movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieDto movieDto, @RequestParam ObjectId showId) {
        try {
            Movie movie = new Movie();
            movie.setId(movieDto.getId());
            movie.setTitle(movieDto.getTitle());
            movie.setLanguage(movieDto.getLanguage());
            movie.setGenre(movieDto.getGenre());
            movie.setDescription(movieDto.getDescription());
            movieService.saveMovie(movie, showId);
            return new ResponseEntity<>(movie, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("exception in saving movie");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Add a new theatre")
    @PostMapping("/save-theater")
    public ResponseEntity<?> saveTheater(@RequestBody TheaterDto theaterDto) {
        try {
            Theater theater = new Theater();
            theater.setCity(theaterDto.getCity());
            theater.setName(theaterDto.getName());
            theaterService.saveTheater(theater);
        } catch (Exception e) {
            log.error("exception in saving theater {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Add a new screen in the theatre")
    @PostMapping("/save-screen")
    public ResponseEntity<Screen> createScreen(@RequestBody ScreenDto screenDto, @RequestParam ObjectId ThtId) {
        try {
            Screen screen = new Screen();
            screen.setScreenId(screenDto.getScreenId());
            screen.setScreenName(screenDto.getScreenName());
            screenService.saveScreen(screen, ThtId);
            return new ResponseEntity<>(screen, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("exception in saving screen{}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Add a new shows to the screen ")
    @PostMapping("/save-shows")
    public ResponseEntity<?> createShow(@RequestBody ShowDto showDto, @RequestParam String scrId) {
        try {
            Show show = new Show();
            show.setShowTime(showDto.getShowTime());
            show.setTicketPrice(showDto.getTicketPrice());
            boolean res = showService.saveShow(show, scrId);
            if (res)
                return new ResponseEntity<>(HttpStatus.CREATED);
            else return new ResponseEntity<>("Show cant be saved as the screen has 0 seats", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("exception in saving show{}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Add seats to the screen")
    @PostMapping("/save-seats")
    public ResponseEntity<?> saveSeats(@RequestBody SeatsDto seatsDto, @RequestParam String scrId) {
        try {
            List<Seats> seats = seatsDto.getSeats();
            seatService.saveSeats(seats, scrId);
            return new ResponseEntity<>(seats, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
