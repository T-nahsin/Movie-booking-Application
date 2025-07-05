package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.entity.Movie;
import com.tnahsin.bookMovies.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/movie")
@Slf4j
@RestController
public class MovieController {

    @Autowired
    MovieService movieService;



    @GetMapping("/browse-movies")
    public ResponseEntity<?> browseMovies(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String city
    ) {

        List<Movie> movies = movieService.filterMovies(language,genre,city);
        if(movies.isEmpty()){
            return new ResponseEntity<>("No movies found",HttpStatus.FOUND);
        }
        return new ResponseEntity<>(movies , HttpStatus.FOUND);
    }
}
