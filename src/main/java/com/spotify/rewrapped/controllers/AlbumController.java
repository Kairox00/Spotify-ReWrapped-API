package com.spotify.rewrapped.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spotify.rewrapped.exceptions.ApiException;
import com.spotify.rewrapped.services.AlbumService;

@Controller
@RequestMapping("/albums")
public class AlbumController {
  private final AlbumService albumService;

  public AlbumController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> getAlbumData(@PathVariable("id") String id) throws ApiException {
    Map<String, Object> data = albumService.getAlbumData(id);
    return ResponseEntity.ok().body(data);
  }
}
