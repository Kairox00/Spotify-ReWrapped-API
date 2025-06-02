package com.spotify.rewrapped.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rewrapped.services.SpotifyUserService;

@RestController
@RequestMapping("/me")
public class UserController {
    private final SpotifyUserService userService;

    public UserController(SpotifyUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/top-artists")
    public Map<String, Object> getTopArtists(
            @RequestParam String time_range,
            @RequestHeader(name = "Authorization", required = true) String access_token) {
        access_token = access_token.split(" ")[1];
        Map<String, Object> result = userService.getTopArtists(access_token, time_range);
        return result;
    }

    @GetMapping("/top-tracks")
    public Map<String, Object> getTopTracks(
            @RequestParam String time_range,
            @RequestHeader(name = "Authorization", required = true) String access_token) {
        access_token = access_token.split(" ")[1];
        Map<String, Object> result = userService.getTopTracks(access_token, time_range);
        return result;
    }
}
