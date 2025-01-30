package com.spotify.rewrapped.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spotify.rewrapped.exceptions.ApiException;
import com.spotify.rewrapped.services.TrackService;

@Controller
@RequestMapping("/tracks")
public class TrackController {
  private TrackService trackService;

  @Autowired
  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> getData(@PathVariable("id") String id) throws ApiException {
    Map<String, Object> data = trackService.getTrackData(id);
    return ResponseEntity.ok().body(data);
  }

}
