package com.spotify.rewrapped.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spotify.rewrapped.dtos.TrackDTO;
import com.spotify.rewrapped.exceptions.ApiException;
import com.spotify.rewrapped.services.TrackService;

@Controller
@RequestMapping("/tracks")
public class TrackController {
  private final TrackService trackService;

  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<TrackDTO> getData(@PathVariable String id) throws ApiException {
    TrackDTO data = trackService.getTrackData(id);
    return ResponseEntity.ok().body(data);
  }

}
