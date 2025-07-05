package com.tnahsin.bookMovies.service;


import com.tnahsin.bookMovies.entity.Movie;
import com.tnahsin.bookMovies.entity.Screen;
import com.tnahsin.bookMovies.entity.Show;
import com.tnahsin.bookMovies.entity.Theater;
import com.tnahsin.bookMovies.repository.MovieRepository;
import com.tnahsin.bookMovies.repository.ShowRepository;
import com.tnahsin.bookMovies.repository.TheaterRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    @Autowired
    ShowRepository showRepository;


    @Autowired
    TheaterRepository theaterRepository;

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void addShowTime(Movie movie , ObjectId showId) {
        Show show = showRepository.findById(showId).get();
        movie.getShows().add(showId);
    }

    public void saveMovie(Movie movie, ObjectId showId) {
        try {
            Show show = showRepository.findById(showId).get();
            movie.getShows().add(showId);
            Movie saved = movieRepository.save(movie);
            show.setMovieId(saved.getId());
            showRepository.save(show);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> filterMovies(String language , String genre , String city) {
        List<Movie> movies;
        if (language != null) {
            movies = movieRepository.findByLanguageIgnoreCase(language);
        } else if (genre != null) {
            movies = movieRepository.findByGenreContainingIgnoreCase(genre);
        } else {
            movies = movieRepository.findAll();
        }
        if(movies == null) {
            log.error("Cant browse movies");
        }
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            List<Theater> theater = theaterRepository.findByCityIgnoreCase(city);
            for (Theater theater1 : theater) {
                for (Screen screen : theater1.getScreens()) {
                    for (Show show : screen.getShows()) {
                        if (show.getMovieId().equals(movie.getId())){
                            result.add(movie);
                        }
                    }
                }
            }
        }
        if(result.isEmpty()){
            log.error("No movies found");
        }
        return result;
    }
}