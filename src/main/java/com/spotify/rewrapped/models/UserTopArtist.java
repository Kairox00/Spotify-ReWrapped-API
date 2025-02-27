package com.spotify.rewrapped.models;

import com.spotify.rewrapped.models.ids.UserTopArtistId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@IdClass(UserTopArtistId.class)
@Data
public class UserTopArtist {
  @Id
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Id
  private String artistId;

  @Id
  private String month;

  @Id
  private String year;

  @Id
  private int rank;

  private String artistName;

}
