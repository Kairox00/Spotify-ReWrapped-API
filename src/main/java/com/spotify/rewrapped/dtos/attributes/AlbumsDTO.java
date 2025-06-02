package com.spotify.rewrapped.dtos.attributes;

import java.util.List;

import com.spotify.rewrapped.dtos.AlbumDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlbumsDTO {
  private String href;
  private int limit;
  private String next;
  private int offset;
  private String previous;
  private int total;
  private List<AlbumDTO> items;
}
