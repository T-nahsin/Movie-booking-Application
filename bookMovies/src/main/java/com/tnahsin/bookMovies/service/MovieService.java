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

    public void saveMovie(Movie movie, ObjectId showId) {
        Boolean isPresent = showRepository.findById(showId).isPresent();
        if(isPresent) {
            Show show = showRepository.findById(showId).get();
            Movie saved = movieRepository.save(movie);
            show.getMovie().add(saved);
            showRepository.save(show);
        }else{
            log.error("Can't get show");
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
        for (Movie movie : movies) {
            List<Theater> theater = theaterRepository.findByCityIgnoreCase(city);
            for (Theater theater1 : theater) {
                for (Screen screen : theater1.getScreens()) {
                    for (Show show : screen.getShows()) {
                        if (!show.getMovie().contains(movie)){
                            movies.remove(movie);
                        }
                    }
                }
            }
        }
        if(movies.isEmpty()){
            log.error("No movies found");
        }
        return movies;
    }
}