package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.dtos.ShowTimeResponse;
import com.tnahsin.bookMovies.entity.Show;
import com.tnahsin.bookMovies.repository.ShowRepository;
import com.tnahsin.bookMovies.service.ShowService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    ShowService showService;

    @Autowired
    ShowRepository showRepository;


    @GetMapping
    public ResponseEntity<List<Show>> getTime(@RequestParam LocalDateTime showTime){
        return new ResponseEntity<>(showRepository.findByShowTime(showTime),HttpStatus.FOUND);
    }



    @GetMapping("/get-showtimes")
    public ResponseEntity<?> getShowTimes(
            @RequestParam String movieId,
            @RequestParam(required = false) ObjectId theaterId) {

        List<ShowTimeResponse> showTimes=new ArrayList<>();
        if(theaterId != null){
        showTimes = showService.getShowTimes(movieId,theaterId);
        }else {
            showTimes = showService.getShowTimes(movieId);
        }
        if (showTimes  == null) {
            return new ResponseEntity<>("Movie is not avl in your city",HttpStatus.FOUND);
        }
        return new ResponseEntity<>(showTimes,HttpStatus.FOUND);
    }
}
