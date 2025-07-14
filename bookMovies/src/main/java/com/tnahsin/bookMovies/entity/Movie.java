package com.tnahsin.bookMovies.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
@Getter
@Setter
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private String genre;
    private String language;

    private List<ObjectId> shows = new ArrayList<>();
}
