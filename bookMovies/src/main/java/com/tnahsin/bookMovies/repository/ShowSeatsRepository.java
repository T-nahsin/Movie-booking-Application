package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.Show;
import com.tnahsin.bookMovies.entity.ShowSeats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatsRepository extends MongoRepository<ShowSeats , String> {

    List<ShowSeats> findByShowId(ObjectId showId);

    List<ShowSeats> findByShowIdAndSeatIdIn(ObjectId showId, List<String> seatIds);
}
