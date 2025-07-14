package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface MovieRepository extends MongoRepository<Movie , String> {

    HashSet<Movie> findByLanguageIgnoreCase(String language);

    HashSet<Movie> findByGenreContainingIgnoreCase(String genre);

}
