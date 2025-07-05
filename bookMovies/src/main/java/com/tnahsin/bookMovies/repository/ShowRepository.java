package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.Show;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShowRepository extends MongoRepository<Show, ObjectId> {

    List<Show> findByShowTime(LocalDateTime time);

    List<Show> findByMovieIdAndShowTime(String movieId , LocalDateTime date);

    List<Show> findByTheaterIdAndMovieIdAndShowTime(ObjectId theaterId, String movieId, LocalDateTime date);
}
