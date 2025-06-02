package com.spotify.rewrapped.connections;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Getter;

@Getter
@Component
public class StatsFMConnection {

  private WebClient client;

  public StatsFMConnection() {
    this.client = WebClient.builder()
        .baseUrl("https://api.stats.fm/api/v1/SPOTIFY/")
        .build();
  }

}
