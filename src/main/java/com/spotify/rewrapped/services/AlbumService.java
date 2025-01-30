package com.spotify.rewrapped.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spotify.rewrapped.connectors.SpotifyConnector;
import com.spotify.rewrapped.exceptions.ApiException;

@Service
public class AlbumService {
  private WebClient client;

  @Autowired
  public AlbumService(SpotifyConnector connector) {
    this.client = connector.getClient();
  }

  private Map<String, Object> getInfo(String Id) throws ApiException {
    try {
      Map<String, Object> response = client.get().uri("albums/" + Id).retrieve()
          .bodyToMono(Map.class).block();
      return response;
    } catch (Exception e) {
      throw new ApiException(e.getMessage(), 500);
    }
  }

  private Map<String, Object> getTracks(String Id) throws ApiException {
    try {
      Map<String, Object> response = client.get().uri("albums/" + Id + "/tracks").retrieve()
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
