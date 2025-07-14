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
    EmailService emailService;


    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User res = userRepository.save(user);

            if (!res.equals(user))
                log.error((Marker) UserService.log, "Can't register user");

            // Send welcome email
            String subject = "Welcome to BookMyShow Clone!";
            String message = "Hi " + user.getUserName() + ",\n\nThank you for registering with us.\n\nEnjoy booking movies! 🎬";
            emailService.sendMail(user.getEmail(), subject, message);

        } catch (Exception e) {
            log.error("Cant register user");
        }
    }
}
