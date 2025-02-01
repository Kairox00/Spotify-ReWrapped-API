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

    public Map<String, Object> getUserInfo(String accessToken) {
        Map<String, Object> response = client.get()
                .uri(builder -> builder.path("me").build())
                .header("Authorization", "Bearer " + accessToken)
                .retrieve().bodyToMono(Map.class).block();
        return response;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getTopArtists(String accessToken, String timeRange) {
        Map<String, Object> response = client.get()
                .uri(builder -> builder.path("me/top/artists").queryParam("time_range", timeRange).build())
                .header("Authorization", "Bearer " + accessToken)
                .retrieve().bodyToMono(Map.class).block();
        return response;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getTopTracks(String accessToken, String timeRange) {
        Map<String, Object> response = client.get()
                .uri(builder -> builder.path("me/top/tracks").queryParam("time_range", timeRange)
                        .queryParam("limit", 50).build())
                .header("Authorization", "Bearer " + accessToken)
                .retrieve().bodyToMono(Map.class).block();
        return response;
    }

}
