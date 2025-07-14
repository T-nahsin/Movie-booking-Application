package com.tnahsin.bookMovies.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    @NonNull
    private String id;
    @NonNull
    private String seatType;
    private String screenId;
}
