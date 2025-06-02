package com.spotify.rewrapped.services;

import org.springframework.stereotype.Service;

import com.spotify.rewrapped.connections.SpotifyConnection;
import com.spotify.rewrapped.dtos.AlbumDTO;
import com.spotify.rewrapped.exceptions.ApiException;

@Service
public class AlbumService {
  private final SpotifyConnection connection;

  public AlbumService(SpotifyConnection connection) {
    this.connection = connection;
  }

  public AlbumDTO getAlbum(String Id) throws ApiException {
    try {
      AlbumDTO response = connection.getClient().get().uri("albums/" + Id).retrieve()
          .bodyToMono(AlbumDTO.class).block();
      return response;
    } catch (Exception e) {
      throw new ApiException(e.getMessage(), 500);
    }
  }
}
