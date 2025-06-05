package com.spotify.rewrapped.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rewrapped.exceptions.ApiException;
import com.spotify.rewrapped.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login() throws ApiException {
        System.out.println("Attempting to login to Spotify");
        Map<String, Object> result = authenticationService.login();
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization, X-API-Key")
                .body(result);
    }

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam String code,
            @RequestParam String state) throws ApiException {
        System.out.println("Received callback");
        if (!state.equals("myState")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, Object> result = authenticationService.callback(code, state);
        if (result.containsKey("error")) {
            return ResponseEntity.badRequest().build();
        }
        System.out.println("User will be redirected to the home page");
        return ResponseEntity.ok(result);
    }
}
