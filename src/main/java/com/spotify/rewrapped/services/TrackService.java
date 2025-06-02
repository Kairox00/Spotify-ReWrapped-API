package com.spotify.rewrapped.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spotify.rewrapped.connections.SpotifyConnection;
import com.spotify.rewrapped.connections.StatsFMConnection;
import com.spotify.rewrapped.exceptions.ApiException;

@Service
public class TrackService {
  private final SpotifyConnection spotifyConnection;
  private final StatsFMConnection statsFMConnection;

  public TrackService(SpotifyConnection spotifyConnection, StatsFMConnection statsFMConnection) {
    this.spotifyConnection = spotifyConnection;
    this.statsFMConnection = statsFMConnection;
  }

  private Map<String, Object> getInfo(String id) throws ApiException {
    try {
      Map<String, Object> response = spotifyConnection.getClient().get().uri("tracks/" + id).retrieve()
          .bodyToMono(Map.class).block();
      return response;
    } catch (Exception e) {
      throw new ApiException(e.getMessage(), 500);
    }
  }

  private Map<String, Object> getAudioFeatures(String id) throws ApiException {
    try {
      Map<String, Object> response = statsFMConnection.getClient().get().uri("audio-features/" + id).retrieve()
          .bodyToMono(Map.class)
          .block();
      return response;
    } catch (Exception e) {
      throw new ApiException(e.getMessage(), 500);
    }
  }

  public Map<String, Object> getTrackData(String id) throws ApiException {
    Map<String, Object> info = getInfo(id);
    Map<String, Object> audioFeatures = getAudioFeatures(id);
    Map<String, Object> result = new HashMap<>();
    result.putAll(info);
    result.put("audioFeatures", audioFeatures);
    return result;
  }

}
