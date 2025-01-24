package com.spotify.rewrapped.models.ids;

import com.spotify.rewrapped.models.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserTopArtistId {
  private User user;
  private String artistId;
  private String month;
  private String year;
  private int rank;
}
