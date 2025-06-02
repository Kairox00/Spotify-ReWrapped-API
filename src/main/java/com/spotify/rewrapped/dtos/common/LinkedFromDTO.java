package com.spotify.rewrapped.dtos.common;

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
