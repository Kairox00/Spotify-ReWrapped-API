package com.spotify.rewrapped.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.rewrapped.dtos.attributes.ArtistRefDTO;
import com.spotify.rewrapped.dtos.attributes.AudioFeaturesDTO;
import com.spotify.rewrapped.dtos.attributes.ExternalIdsDTO;
import com.spotify.rewrapped.dtos.attributes.ExternalUrlsDTO;
import com.spotify.rewrapped.dtos.attributes.ImageDTO;
import com.spotify.rewrapped.dtos.attributes.LinkedFromDTO;
import com.spotify.rewrapped.dtos.attributes.RestrictionsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrackDTO {

  private AlbumDTO album;

  private List<ArtistRefDTO> artists;

  @JsonProperty("available_markets")
  private List<String> availableMarkets;

  @JsonProperty("disc_number")
  private Integer discNumber;

  @JsonProperty("duration_ms")
  private Integer durationMs;

  private Boolean explicit;

  private String href;

  private String id;

  @JsonProperty("is_playable")
  private Boolean isPlayable;

  @JsonProperty("linked_from")
  private LinkedFromDTO linkedFrom;

  private String name;

  private Integer popularity;

  @JsonProperty("preview_url")
  private String previewUrl;

  @JsonProperty("track_number")
  private Integer trackNumber;

  private String type;
  private String uri;

  @JsonProperty("is_local")
  private Boolean isLocal;

  @JsonProperty("external_ids")
  private ExternalIdsDTO externalIds;

  @JsonProperty("external_urls")
  private ExternalUrlsDTO externalUrls;

  private RestrictionsDTO restrictions;

  private ImageDTO image;

  private AudioFeaturesDTO audioFeatures;

  public void setAudioFeatures(AudioFeaturesDTO audioFeatures) {
    this.audioFeatures = audioFeatures;
  }

}
