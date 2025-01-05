package com.spotify.rewrapped.models;

import com.spotify.rewrapped.models.ids.UserTopArtistsId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_top_artists")
@IdClass(UserTopArtistsId.class)
@Data
public class UserTopArtists {
  @Id
  private int userId;

  @Id
  private String artistId;

  @Id
  private String month;

  @Id
  private String year;

  private String artistName;
  private String[] artistGenres;
}
