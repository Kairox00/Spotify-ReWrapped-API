package com.spotify.rewrapped.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrackDTO extends TrackInfoDTO {

  public TrackDTO(TrackInfoDTO trackInfo, AudioFeaturesDTO audioFeatures) {
    super(trackInfo.getAlbum(), trackInfo.getArtists(), trackInfo.getAvailableMarkets(),
        trackInfo.getDiscNumber(), trackInfo.getDurationMs(), trackInfo.getExplicit(),
        trackInfo.getExternalIds(), trackInfo.getExternalUrls(), trackInfo.getHref(),
        trackInfo.getId(), trackInfo.getIsPlayable(), trackInfo.getLinkedFrom(),
        trackInfo.getRestrictions(), trackInfo.getName(), trackInfo.getPopularity(),
        trackInfo.getPreviewUrl(), trackInfo.getTrackNumber(), trackInfo.getType(),
        trackInfo.getUri(), trackInfo.getIsLocal());
    this.audioFeatures = audioFeatures;
  }

  @JsonProperty("audioFeatures")
  private AudioFeaturesDTO audioFeatures;
}
