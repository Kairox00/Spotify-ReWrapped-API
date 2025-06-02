package com.spotify.rewrapped.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrackInfoDTO {

  private Album album;

  private List<Artist> artists;

  @JsonProperty("available_markets")
  private List<String> availableMarkets;

  @JsonProperty("disc_number")
  private Integer discNumber;

  @JsonProperty("duration_ms")
  private Integer durationMs;

  private Boolean explicit;

  @JsonProperty("external_ids")
  private ExternalIds externalIds;

  @JsonProperty("external_urls")
  private ExternalUrls externalUrls;

  private String href;
  private String id;

  @JsonProperty("is_playable")
  private Boolean isPlayable;

  @JsonProperty("linked_from")
  private Object linkedFrom; // Empty object — use Object or Map<String, Object>

  private Restrictions restrictions;
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

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Album {
    @JsonProperty("album_type")
    private String albumType;

    @JsonProperty("total_tracks")
    private Integer totalTracks;

    @JsonProperty("available_markets")
    private List<String> availableMarkets;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    private String href;
    private String id;
    private List<Image> images;
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;

    private Restrictions restrictions;
    private String type;
    private String uri;
    private List<Artist> artists;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Artist {
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private String name;
    private String type;
    private String uri;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ExternalIds {
    private String isrc;
    private String ean;
    private String upc;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ExternalUrls {
    private String spotify;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Image {
    private String url;
    private Integer height;
    private Integer width;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Restrictions {
    private String reason;
  }
}
