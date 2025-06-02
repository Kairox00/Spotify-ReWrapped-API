package com.spotify.rewrapped.dtos;

import java.util.List;

import com.spotify.rewrapped.dtos.attributes.CopyrightDTO;
import com.spotify.rewrapped.dtos.attributes.ExternalIdsDTO;
import com.spotify.rewrapped.dtos.attributes.ExternalUrlsDTO;
import com.spotify.rewrapped.dtos.attributes.ImageDTO;
import com.spotify.rewrapped.dtos.attributes.RestrictionsDTO;
import com.spotify.rewrapped.dtos.attributes.TracksDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlbumDTO {
  private String album_type;
  private int total_tracks;
  private List<String> available_markets;
  private ExternalUrlsDTO external_urls;
  private String href;
  private String id;
  private List<ImageDTO> images;
  private String name;
  private String release_date;
  private String release_date_precision;
  private RestrictionsDTO restrictions;
  private String type;
  private String uri;
  private List<ArtistDTO> artists;
  private TracksDTO tracks;
  private List<CopyrightDTO> copyrights;
  private ExternalIdsDTO external_ids;
  private List<String> genres;
  private String label;
  private int popularity;
}
