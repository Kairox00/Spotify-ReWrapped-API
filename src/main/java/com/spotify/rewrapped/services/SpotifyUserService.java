package com.spotify.rewrapped.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spotify.rewrapped.connections.SpotifyConnection;

@Service
public class SpotifyUserService {
  @Autowired
  private SpotifyConnection connector;

  @SuppressWarnings("unchecked")
  public Map<String, Object> getUserInfo(String accessToken) {
    WebClient client = connector.getClient();
    client = client.mutate().defaultHeader("Authorization", "Bearer " + accessToken).build();

    Map<String, Object> response = client
        .get()
        .uri(builder -> builder.path("me").build())
        .retrieve().bodyToMono(Map.class).block();
    return response;
  }

  @SuppressWarnings("unchecked")
  public Map<String, Object> getTopArtists(String accessToken, String timeRange) {
    WebClient client = connector.getClient();
    client = client.mutate().defaultHeader("Authorization", "Bearer " + accessToken).build();
    Map<String, Object> response = client.get()
        .uri(builder -> builder.path("me/top/artists").queryParam("time_range", timeRange).build())
        .retrieve().bodyToMono(Map.class).block();
    return response;
  }

  @SuppressWarnings("unchecked")
  public Map<String, Object> getTopTracks(String accessToken, String timeRange) {
    WebClient client = connector.getClient();
    client = client.mutate().defaultHeader("Authorization", "Bearer " + accessToken).build();
    Map<String, Object> response = client.get()
        .uri(builder -> builder.path("me/top/tracks").queryParam("time_range", timeRange)
            .queryParam("limit", 50).build())
        .retrieve().bodyToMono(Map.class).block();
    return response;
  }

}
