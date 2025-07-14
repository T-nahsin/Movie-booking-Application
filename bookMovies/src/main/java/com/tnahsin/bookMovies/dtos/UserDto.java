package com.tnahsin.bookMovies.dtos;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private String userName;
    private String password;
    private List<String> role;
    private String email;
    private String city;
}
