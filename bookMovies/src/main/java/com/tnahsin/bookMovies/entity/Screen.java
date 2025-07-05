package com.tnahsin.bookMovies.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Screen {
    @Id
    private String screenId;
    private String screenName;

    private ObjectId theaterId;

    @DBRef
    private List<Show> shows = new ArrayList<>();

    @DBRef
    private List<Seats> seats = new ArrayList<>();
}
