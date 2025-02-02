package com.spotify.rewrapped.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spotify.rewrapped.connectors.SpotifyConnector;
import com.spotify.rewrapped.models.User;
import com.spotify.rewrapped.repositories.UserRepository;

@Service
public class UserService {
    private WebClient client;
    private UserRepository userRepository;

    @Autowired
    public UserService(SpotifyConnector connector, UserRepository userRepository) {
        this.client = connector.getClient();
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
