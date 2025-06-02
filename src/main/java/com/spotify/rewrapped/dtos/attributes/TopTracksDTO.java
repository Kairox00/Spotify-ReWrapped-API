package com.spotify.rewrapped.dtos.attributes;

import java.util.List;

import com.spotify.rewrapped.dtos.TrackDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TopTracksDTO {
  private List<TrackDTO> tracks;
}
