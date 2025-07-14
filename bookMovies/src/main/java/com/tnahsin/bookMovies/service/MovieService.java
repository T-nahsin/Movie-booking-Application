package com.tnahsin.bookMovies.service;


import com.tnahsin.bookMovies.dtos.MovieResponse;
import com.tnahsin.bookMovies.dtos.SearchMovieDto;
import com.tnahsin.bookMovies.entity.Movie;
import com.tnahsin.bookMovies.entity.Screen;
import com.tnahsin.bookMovies.entity.Show;
import com.tnahsin.bookMovies.entity.Theater;
import com.tnahsin.bookMovies.repository.MovieRepository;
import com.tnahsin.bookMovies.repository.ScreenRepository;
import com.tnahsin.bookMovies.repository.ShowRepository;
import com.tnahsin.bookMovies.repository.TheaterRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    @Autowired
    ShowRepository showRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void addShowTime(Movie movie , ObjectId showId) {
        Show show = showRepository.findById(showId).get();
        show.setMovieId(movie.getId());
        showRepository.save(show);
        movie.getShows().add(showId);
        movieRepository.save(movie);
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

    public List<MovieResponse> filterMovies(SearchMovieDto searchMovieDto) {
        List<MovieResponse> movieResponses = new ArrayList<>();
        String language = searchMovieDto.getLanguage();
        String genre = searchMovieDto.getGenre();
        HashSet<Movie> movies = new HashSet<>(movieRepository.findByLanguageIgnoreCase(language));

        if (genre != null) {
            HashSet<Movie>movie2 = movieRepository.findByGenreContainingIgnoreCase(genre);
            movies.addAll(movie2);
        }
        for(Movie movie : movies) {
            List<ObjectId> shows = movie.getShows();
            for (ObjectId show1: shows) {
                Show show = showRepository.findById(show1).get();
                MovieResponse response = new MovieResponse();
                response.setMovieName(movie.getTitle());
                Theater theater = theaterRepository.findById(show.getTheaterId()).get();
                response.setTheaterName(theater.getName());
                Screen screen = screenRepository.findById(show.getScreenId()).get();
                response.setScreenName(screen.getScreenName());
                response.setShowTime(show.getShowTime());
                movieResponses.add(response);
            }
        }
        return movieResponses;
    }
}