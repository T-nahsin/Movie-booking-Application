package com.tnahsin.bookMovies.controller;


import com.tnahsin.bookMovies.entity.User;
import com.tnahsin.bookMovies.service.UserService;
import com.tnahsin.bookMovies.utilis.JwtUtil;
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
public class AuthController {


    @Autowired
    JwtUtil jwtUtil;


    @Autowired
    UserService userService;


    @Autowired
    AuthenticationManager authenticationManager;



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
       userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
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