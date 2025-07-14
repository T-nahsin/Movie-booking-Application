package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.dtos.UserDto;
import com.tnahsin.bookMovies.entity.User;
import com.tnahsin.bookMovies.service.UserService;
import com.tnahsin.bookMovies.utilis.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/auth")
@RestController
@Tag(name = "Authorization APIs" ,description = "Register and login users")
public class AuthController {


    @Autowired
    JwtUtil jwtUtil;


    @Autowired
    UserService userService;


    @Autowired
    AuthenticationManager authenticationManager;



    @Operation(summary = "Register new users")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setEmail(userDto.getEmail());
        user.setCity(userDto.getCity());
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @Operation(summary = "Login to the existing user")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            String token = jwtUtil.generateToken(user);
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }catch(Exception e) {
             log.error("not authenticated due to exception");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}