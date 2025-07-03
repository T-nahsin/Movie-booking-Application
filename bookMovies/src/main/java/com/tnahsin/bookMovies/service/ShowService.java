package com.tnahsin.bookMovies.service;


import com.tnahsin.bookMovies.entity.Screen;
import com.tnahsin.bookMovies.entity.Show;
import com.tnahsin.bookMovies.entity.Theater;
import com.tnahsin.bookMovies.repository.ScreenRepository;
import com.tnahsin.bookMovies.repository.ShowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ShowService {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    ShowRepository showRepository;


    public void saveShow(Show show, String screenId) {
        Screen screen = screenRepository.findById(screenId).get();
        Show saved = showRepository.save(show);
        screen.getShows().add(saved);
        screenRepository.save(screen);
    }
}
