package com.spotify.rewrapped.services;

import org.springframework.stereotype.Service;

import com.spotify.rewrapped.models.User;
import com.spotify.rewrapped.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

}
