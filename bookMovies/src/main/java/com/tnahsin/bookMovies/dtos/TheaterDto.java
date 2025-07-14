package com.tnahsin.bookMovies.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {
    private String name;
    @NonNull
    private String city;
}
