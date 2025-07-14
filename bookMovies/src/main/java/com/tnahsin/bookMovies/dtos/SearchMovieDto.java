package com.tnahsin.bookMovies.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SearchMovieDto {

    private String language;
    private String genre;
}
