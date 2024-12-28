package com.spotify.rewrapped.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spotify.rewrapped.connectors.SpotifyConnector;

@Service
public class UserService {
    private WebClient client;

    @Autowired
    public UserService(SpotifyConnector connector) {
        this.client = connector.getClient();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getTopArtists(String accessToken, String timeRange) {
        try {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("time_range", timeRange);
            Map<String, Object> response = client.get().uri("me/top/artists")
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve().bodyToMono(Map.class).block();
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Map error = (Map) new HashMap<>();
            error.put("error", e.getMessage());
            return error;
        }
    }

}
