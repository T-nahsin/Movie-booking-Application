package com.tnahsin.bookMovies.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "theaters")
public class Theater {
    @Id
    private ObjectId id;
    private String name;
    private String city;
    @DBRef
    private List<Screen> screens = new ArrayList<>();
}
