package com.spotify.rewrapped.dtos.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AudioFeaturesDTO {
  private Item item;

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Item {
    @JsonProperty("acousticness")
    private Float acousticness;

    @JsonProperty("createdAt")
    private Long createdAt;

    @JsonProperty("danceability")
    private Float danceability;

    @JsonProperty("duration_ms")
    private Integer durationMs;

    @JsonProperty("energy")
    private Float energy;

    @JsonProperty("instrumentalness")
    private Float instrumentalness;

    @JsonProperty("key")
    private Integer key;

    @JsonProperty("liveness")
    private Float liveness;

    @JsonProperty("loudness")
    private Float loudness;

    @JsonProperty("mode")
    private Integer mode;

    @JsonProperty("speechiness")
    private Float speechiness;

    @JsonProperty("tempo")
    private Float tempo;

    @JsonProperty("time_signature")
    private Integer timeSignature;

    @JsonProperty("valence")
    private Float valence;
  }
}
