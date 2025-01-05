package com.spotify.rewrapped.models.ids;

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
public class UserTopArtistsId {
  private int userId;
  private String artistId;
  private String month;
  private String year;
}
