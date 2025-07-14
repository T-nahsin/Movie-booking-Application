package com.tnahsin.bookMovies.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {


    private String id;
    private String title;
    private String description;
    private String genre;
    private String language;


}
