package com.tnahsin.bookMovies.dtos;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private String movieName ;
    private LocalDateTime showTime;
    private String theaterName;
    private String screenName;
}
