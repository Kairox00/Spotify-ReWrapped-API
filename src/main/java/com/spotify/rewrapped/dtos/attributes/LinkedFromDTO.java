package com.spotify.rewrapped.dtos.attributes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LinkedFromDTO {
  private ExternalUrlsDTO external_urls;
  private String href;
  private String id;
  private String type;
  private String uri;
}
