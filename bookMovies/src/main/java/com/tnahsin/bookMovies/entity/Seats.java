package com.tnahsin.bookMovies.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Seats {
    @Id
    private ObjectId SeatId;
    private String seatNumber;
    private String seatType;

}
