package com.tnahsin.bookMovies.dtos;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {
    @NonNull
    private LocalDateTime showTime;
    private double ticketPrice;
}
