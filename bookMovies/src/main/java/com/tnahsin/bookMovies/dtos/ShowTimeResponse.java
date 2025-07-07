package com.tnahsin.bookMovies.dtos;


import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeResponse {
    private String theaterName;
    private String screenName;
    private String movieName;
    private LocalDateTime showTime;


}
