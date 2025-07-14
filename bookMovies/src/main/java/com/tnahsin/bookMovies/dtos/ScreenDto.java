package com.tnahsin.bookMovies.dtos;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenDto {
    @NonNull
    private String screenId;
    @NonNull
    private String screenName;
}
