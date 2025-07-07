package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.Show;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<Show, ObjectId> {
}
