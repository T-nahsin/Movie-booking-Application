package com.tnahsin.bookMovies.service;


import com.tnahsin.bookMovies.entity.Screen;
import com.tnahsin.bookMovies.entity.Theater;
import com.tnahsin.bookMovies.repository.ScreenRepository;
import com.tnahsin.bookMovies.repository.TheaterRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScreenService {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public void saveScreen(Screen screen, ObjectId theaterId) {
        Theater theater = theaterRepository.findById(theaterId).get();
        screen.setTheaterId(theaterId);
            Screen saved = screenRepository.save(screen);
            theater.getScreens().add(saved);
            theaterRepository.save(theater);
    }
}
