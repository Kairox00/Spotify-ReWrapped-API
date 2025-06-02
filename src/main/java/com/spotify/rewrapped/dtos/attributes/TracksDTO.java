package com.spotify.rewrapped.dtos.attributes;

import java.util.List;

import com.spotify.rewrapped.dtos.TrackDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TracksDTO {
  private String href;
  private int limit;
  private String next;
  private int offset;
  private String previous;
  private int total;
  private List<TrackDTO> items;
}
