package com.spotify.rewrapped.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spotify.rewrapped.connectors.SpotifyConnector;

@Service
public class TrackService {
  private WebClient spotifyClient;
  private WebClient statsFMClient;

  @Autowired
  public TrackService(SpotifyConnector connector) {
    this.spotifyClient = connector.getClient();
    this.statsFMClient = WebClient.builder()
        .baseUrl("https://api.stats.fm/api/v1/SPOTIFY/")
        .build();
  }

  public Map<String, Object> getInfo(String id) {
    try {
      Map<String, Object> response = spotifyClient.get().uri("tracks/" + id).retrieve().bodyToMono(Map.class).block();
      return response;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public Map<String, Object> getAudioFeatures(String id) {
    try {
      Map<String, Object> response = statsFMClient.get().uri("audio-features/" + id).retrieve().bodyToMono(Map.class)
          .block();
      return response;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public Map<String, Object> getTrackData(String id) {
    Map<String, Object> info = getInfo(id);
    Map<String, Object> audioFeatures = getAudioFeatures(id);
    Map<String, Object> result = new HashMap<>();
    result.putAll(info);
    result.put("audioFeatures", audioFeatures);
    return result;
  }

}
