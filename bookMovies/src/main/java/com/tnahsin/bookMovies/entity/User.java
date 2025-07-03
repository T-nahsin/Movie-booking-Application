package com.tnahsin.bookMovies.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document(collection = "User")
@Getter
@Setter
@NoArgsConstructor
public class User {

    private ObjectId id;

    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String role;
    private String email;
}
