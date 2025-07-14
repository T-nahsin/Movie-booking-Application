package com.tnahsin.bookMovies.dtos;


import com.tnahsin.bookMovies.entity.Seats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatsDto {

    private List<Seats> seats;
}
