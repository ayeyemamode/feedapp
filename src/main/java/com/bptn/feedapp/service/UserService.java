package com.bptn.feedapp.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bptn.feedapp.jpa.User;
import com.bptn.feedapp.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public List<User> listUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void createUser(User user) {
        this.userRepository.save(user);
    }

    public User signup(User user) {
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmailId(user.getEmailId().toLowerCase());
        user.setEmailVerified(false);
        user.setCreatedOn(Timestamp.from(Instant.now()));

        // Save the user to the database
        this.userRepository.save(user);

        // Send verification email
        this.emailService.sendVerificationEmail(user);

        return user;
    }
}
