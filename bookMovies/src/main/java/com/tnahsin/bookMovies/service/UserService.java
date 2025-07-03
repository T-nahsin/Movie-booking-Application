package com.tnahsin.bookMovies.service;

import com.tnahsin.bookMovies.entity.User;
import com.tnahsin.bookMovies.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User res = userRepository.save(user);

            if (!res.equals(user))
                log.error((Marker) UserService.log, "Can't register user");

        } catch (Exception e) {
            log.error("hehehehehehehehe");
        }
    }
}
