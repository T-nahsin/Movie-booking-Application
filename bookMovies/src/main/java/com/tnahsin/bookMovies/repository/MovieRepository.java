package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie , String> {

    List<Movie> findByLanguageIgnoreCase(String language);

    List<Movie> findByGenreContainingIgnoreCase(String genre);

}
