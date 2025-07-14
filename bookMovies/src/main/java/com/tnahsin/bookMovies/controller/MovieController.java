package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.dtos.MovieResponse;
import com.tnahsin.bookMovies.dtos.SearchMovieDto;
import com.tnahsin.bookMovies.entity.Movie;
import com.tnahsin.bookMovies.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/movie")
@Slf4j
@RestController
@Tag(name = "Browse movies")
public class MovieController {

    @Autowired
    MovieService movieService;



    @Operation(summary = "Check the movies of your genre and language")
    @PostMapping("/browse-movies")
    public ResponseEntity<?> browseMovies(
            @RequestBody SearchMovieDto searchMovieDto
    ) {

        List<MovieResponse> movies = movieService.filterMovies(searchMovieDto);
        if(movies.isEmpty()){
            return new ResponseEntity<>("No movies found",HttpStatus.FOUND);
        }
        return new ResponseEntity<>(movies , HttpStatus.FOUND);
    }
}