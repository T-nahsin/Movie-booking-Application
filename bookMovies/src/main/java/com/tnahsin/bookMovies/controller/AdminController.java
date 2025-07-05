package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.entity.Movie;
import com.tnahsin.bookMovies.entity.Screen;
import com.tnahsin.bookMovies.entity.Show;
import com.tnahsin.bookMovies.entity.Theater;
import com.tnahsin.bookMovies.repository.MovieRepository;
import com.tnahsin.bookMovies.service.MovieService;
import com.tnahsin.bookMovies.service.ScreenService;
import com.tnahsin.bookMovies.service.ShowService;
import com.tnahsin.bookMovies.service.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/admin")
@RestController
@Slf4j
@PreAuthorize("hasRole('admin')")
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

    @PostMapping("/add-movie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try{
            movieRepository.save(movie);
            return new ResponseEntity<>(movie,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured in saving movie{}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("/add-showtime")
    public ResponseEntity<?> addShowTime(@RequestBody Movie movie , @RequestParam ObjectId showId){
        try{
            movieService.addShowTime(movie,showId);
            return new ResponseEntity<>(movie,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured in add shows{}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/save-movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie, @RequestParam ObjectId showId) {
        try{
            movieService.saveMovie(movie,showId);
            return new ResponseEntity<>(movie,HttpStatus.CREATED);
        }catch(Exception e) {
            log.error("exception in saving movie");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/save-theater")
    public ResponseEntity<?> saveTheater(@RequestBody Theater theater) {
        try{
            theaterService.saveTheater(theater);
        }catch(Exception e) {
            log.error("exception in saving theater {}",String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/save-screen")
    public ResponseEntity<Screen> createScreen(@RequestBody Screen screen , @RequestParam ObjectId ThtId){
        try{
            screenService.saveScreen(screen,ThtId);
            return new ResponseEntity<>(screen,HttpStatus.CREATED);
        }catch(Exception e) {
            log.error("exception in saving screen{}",String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/save-shows")
    public ResponseEntity<?> createShow(@RequestBody Show show , @RequestParam String scrId) {
        try {
            showService.saveShow(show, scrId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            log.error("exception in saving show{}",String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
