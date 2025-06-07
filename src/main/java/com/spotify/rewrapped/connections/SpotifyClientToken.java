package com.spotify.rewrapped.connections;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpotifyClientToken {
  private String value;
  private Date expiryDate;
}
