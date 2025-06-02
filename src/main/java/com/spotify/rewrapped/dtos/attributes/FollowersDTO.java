package com.spotify.rewrapped.dtos.attributes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FollowersDTO {
  private String href;
  private int total;
}
