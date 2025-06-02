package com.spotify.rewrapped.dtos.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArtistRefDTO {
  @JsonProperty("external_urls")
  private ExternalUrlsDTO externalUrls;
  private String href;
  private String id;
  private String name;
  private String type;
  private String uri;
}
