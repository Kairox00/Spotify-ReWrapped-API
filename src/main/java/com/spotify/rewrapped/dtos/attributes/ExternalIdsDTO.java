package com.spotify.rewrapped.dtos.attributes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExternalIdsDTO {
  private String isrc;
  private String ean;
  private String upc;
}