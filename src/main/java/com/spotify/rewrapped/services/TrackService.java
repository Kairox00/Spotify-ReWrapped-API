package com.spotify.rewrapped.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spotify.rewrapped.connections.SpotifyConnection;
import com.spotify.rewrapped.connections.StatsFMConnection;
import com.spotify.rewrapped.dtos.TrackDTO;
import com.spotify.rewrapped.dtos.attributes.AudioFeaturesDTO;
import com.spotify.rewrapped.exceptions.ApiException;

@Service
public class TrackService {
  private final SpotifyConnection spotifyConnection;
  private final StatsFMConnection statsFMConnection;

  public TrackService(SpotifyConnection spotifyConnection, StatsFMConnection statsFMConnection) {
    this.spotifyConnection = spotifyConnection;
    this.statsFMConnection = statsFMConnection;
  }

  private TrackDTO getInfo(String id, String access_token) throws ApiException {
    try {
      System.out.println("Getting track info");
      WebClient client = spotifyConnection.getClient();
      client = client.mutate().defaultHeader("Authorization", "Bearer " + access_token).build();
      TrackDTO response = spotifyConnection.getClient().get().uri("tracks/" + id).retrieve()
          .bodyToMono(TrackDTO.class).block();
      return response;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      throw new ApiException(e.getMessage(), 500);
    }
  }

  private AudioFeaturesDTO getAudioFeatures(String id) throws ApiException {
    try {
      AudioFeaturesDTO response = statsFMConnection.getClient().get().uri("audio-features/" + id).retrieve()
          .bodyToMono(AudioFeaturesDTO.class)
          .block();
      return response;
    } catch (Exception e) {
      throw new ApiException(e.getMessage(), 500);
    }
  }

  public TrackDTO getTrackData(String id, String access_token) throws ApiException {
    TrackDTO track = getInfo(id, access_token);
    AudioFeaturesDTO audioFeatures = getAudioFeatures(id);
    track.setAudioFeatures(audioFeatures);
    return track;
  }

}
