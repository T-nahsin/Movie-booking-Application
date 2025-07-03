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
import org.springframework.web.bind.annotation.*;


@RequestMapping("/admin")
@RestController
@Slf4j
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

    @PostMapping("/save-movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie, @RequestParam ObjectId showId) {
        try{
            movieService.saveMovie(movie,showId);
            return new ResponseEntity<>(movie,HttpStatus.CREATED);
        }catch(Exception e) {
            log.error("exception in saving screen");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public String hello(){
        return "working";
    }



    @PostMapping("/save-theater")
    public ResponseEntity<?> saveTheater(@RequestBody Theater theater) {
        try{
            theaterService.saveTheater(theater);
        }catch(Exception e) {
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
            log.error("exception in saving screen");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/save-shows")
    public ResponseEntity<?> createShow(@RequestBody Show show , @RequestParam String scrId) {
        try {
            showService.saveShow(show, scrId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            log.error("exception in saving show");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
