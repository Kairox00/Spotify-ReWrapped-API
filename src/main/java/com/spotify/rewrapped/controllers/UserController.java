package com.spotify.rewrapped.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rewrapped.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/top-artists")
    public Map<String, Object> getTopArtists(@RequestParam String time_range, @RequestParam String access_token) {
        Map<String, Object> result = userService.getTopArtists(access_token, time_range);
        return result;
    }
}
