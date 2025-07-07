package com.tnahsin.bookMovies.entity;


import com.tnahsin.bookMovies.dtos.SeatStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "showSeats")
public class ShowSeats {

    @Id
    private String id;

    private String seatId;
    private ObjectId showId;
    private SeatStatus status;
    private LocalDateTime blockedAt;
    private ObjectId blockedByUserId;
}
