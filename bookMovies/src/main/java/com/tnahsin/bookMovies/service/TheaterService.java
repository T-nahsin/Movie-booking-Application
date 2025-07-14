package com.tnahsin.bookMovies.service;


import com.tnahsin.bookMovies.entity.Screen;
import com.tnahsin.bookMovies.entity.Theater;
import com.tnahsin.bookMovies.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public void saveTheater(Theater theater) {
        theaterRepository.save(theater);
    }

}
