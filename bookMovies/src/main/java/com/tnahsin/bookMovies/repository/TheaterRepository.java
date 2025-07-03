package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.Theater;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends MongoRepository<Theater , ObjectId> {
    List<Theater> findByCityIgnoreCase(String city);
}
