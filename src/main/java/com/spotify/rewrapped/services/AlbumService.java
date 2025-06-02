package com.spotify.rewrapped.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spotify.rewrapped.connections.SpotifyConnection;
import com.spotify.rewrapped.exceptions.ApiException;

@Service
public class AlbumService {
  private final SpotifyConnection connection;

  public AlbumService(SpotifyConnection connection) {
    this.connection = connection;
  }

  private Map<String, Object> getInfo(String Id) throws ApiException {
    try {
      Map<String, Object> response = connection.getClient().get().uri("albums/" + Id).retrieve()
          .bodyToMono(Map.class).block();
      return response;
    } catch (Exception e) {
      throw new ApiException(e.getMessage(), 500);
    }
  }

  private Map<String, Object> getTracks(String Id) throws ApiException {
    try {
      Map<String, Object> response = connection.getClient().get().uri("albums/" + Id + "/tracks").retrieve()
          .bodyToMono(Map.class).block();
      return response;
    } catch (Exception e) {
      throw new ApiException(e.getMessage(), 500);
    }
  }

  public Map<String, Object> getAlbumData(String id) throws ApiException {
    Map<String, Object> info = getInfo(id);
    Map<String, Object> tracks = getTracks(id);
    Map<String, Object> result = new HashMap<>();
    result.putAll(info);
    result.put("tracks", tracks);
    return result;
  }
}
