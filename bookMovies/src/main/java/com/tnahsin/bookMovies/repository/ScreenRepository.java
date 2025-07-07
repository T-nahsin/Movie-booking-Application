package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.Screen;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ScreenRepository extends MongoRepository<Screen,String> {
    Optional<Screen> findById(String screen);
}
