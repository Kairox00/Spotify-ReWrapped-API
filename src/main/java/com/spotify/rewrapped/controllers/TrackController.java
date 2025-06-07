package com.spotify.rewrapped.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rewrapped.dtos.TrackDTO;
import com.spotify.rewrapped.exceptions.ApiException;
import com.spotify.rewrapped.services.TrackService;

@RestController
@RequestMapping("/tracks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrackController {
  private final TrackService trackService;

  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<TrackDTO> getData(@PathVariable String id,
      @RequestHeader(name = "Authorization", required = true) String access_token) throws ApiException {
    System.out.println("Received track request");
    TrackDTO data = trackService.getTrackData(id, access_token);
    return ResponseEntity.ok().body(data);
  }

}
