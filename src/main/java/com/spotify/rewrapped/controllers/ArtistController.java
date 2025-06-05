package com.spotify.rewrapped.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rewrapped.dtos.ArtistDTO;
import com.spotify.rewrapped.services.ArtistService;

@RestController
@RequestMapping("/artists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArtistController {

  private final ArtistService artistService;

  public ArtistController(ArtistService artistService) {
    this.artistService = artistService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArtistDTO> getData(@PathVariable("id") String id) {
    ArtistDTO data = artistService.getArtistData(id);
    return ResponseEntity.ok().body(data);
  }

}
