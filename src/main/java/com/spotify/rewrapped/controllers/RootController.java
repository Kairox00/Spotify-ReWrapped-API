package com.spotify.rewrapped.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {
  @GetMapping("/")
  public ResponseEntity<Map> index() {
    Map<String, String> responseBody = new HashMap<>();
    responseBody.put("status", "ok");
    responseBody.put("data", "Welcome to ReWrapped");
    return ResponseEntity.ok().body(responseBody);
  }
}
