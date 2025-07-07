package com.tnahsin.bookMovies.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "shows")
public class Show {
    @Id
    private ObjectId id;

    @NonNull
    private LocalDateTime showTime;
    @NonNull
    private double ticketPrice;

    private String screenId;
    private ObjectId theaterId;
    private String movieId;


    List<ShowSeats> showSeats = new ArrayList<>();
}
