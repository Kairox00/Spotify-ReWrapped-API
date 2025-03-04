package com.spotify.rewrapped.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rewrapped.services.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {
  @Autowired
  private ArtistService artistService;

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> getData(@PathVariable("id") String id) {
    Map<String, Object> data = artistService.getArtistData(id);
    return ResponseEntity.ok().body(data);
  }

}
