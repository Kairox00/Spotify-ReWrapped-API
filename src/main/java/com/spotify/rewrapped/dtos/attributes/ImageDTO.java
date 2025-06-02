package com.spotify.rewrapped.dtos.attributes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ImageDTO {
  private String url;
  private int height;
  private int width;
}
