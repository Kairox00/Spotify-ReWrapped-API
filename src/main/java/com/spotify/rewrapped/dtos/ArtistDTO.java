package com.spotify.rewrapped.dtos;

import java.util.List;

import com.spotify.rewrapped.dtos.attributes.AlbumsDTO;
import com.spotify.rewrapped.dtos.attributes.ExternalUrlsDTO;
import com.spotify.rewrapped.dtos.attributes.FollowersDTO;
import com.spotify.rewrapped.dtos.attributes.ImageDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArtistDTO {
  private ExternalUrlsDTO external_urls;
  private FollowersDTO followers;
  private List<String> genres;
  private String href;
  private String id;
  private List<ImageDTO> images;
  private String name;
  private int popularity;
  private String type;
  private String uri;

  @Setter
  private AlbumsDTO albums;

  @Setter
  private List<TrackDTO> tracks;

}
