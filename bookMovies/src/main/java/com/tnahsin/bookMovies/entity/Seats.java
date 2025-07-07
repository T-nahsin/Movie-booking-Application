package com.tnahsin.bookMovies.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "seat")
public class Seats {
    @Id
    private String id;
    private String seatType;
    private String screenId;
}
