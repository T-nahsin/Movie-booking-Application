package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.Seats;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends MongoRepository<Seats, String> {
    List<Seats> findByScreenId(String screenId);
    List<Seats> findById(List<String> seatId);
}
