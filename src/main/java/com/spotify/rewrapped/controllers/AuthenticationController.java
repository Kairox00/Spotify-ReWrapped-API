package com.spotify.rewrapped.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spotify.rewrapped.connectors.SpotifyConnector;
import com.spotify.rewrapped.exceptions.ApiException;
import com.spotify.rewrapped.models.User;
import com.spotify.rewrapped.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private SpotifyConnector connector;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> data) throws ApiException {
        String email = (String) data.get("email");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return signup(data);
        }
        if (user.getRefreshToken() == null) {
            throw new ApiException("User does not have refresh token", HttpStatus.BAD_REQUEST.value());
        }
        Map<String, Object> result = connector.getNewAccessToken(user.getRefreshToken());
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity<Map<String, Object>> signup(@RequestBody Map<String, Object> data) throws ApiException {
        String clientId = connector.getClientId();
        String scope = "user-read-private user-top-read user-read-email";
        String email = (String) data.get("email");
        Boolean hasUser = userService.getUserByEmail(email) != null;
        if (hasUser) {
            throw new ApiException("Invalid input", HttpStatus.BAD_REQUEST.value());
        }
        User newUser = userService.createUser(email);
        String state = newUser.getHashCode();
        String redirectUri = connector.getRedirectURI();
        String oAuthUrl = String.format(
                "https://accounts.spotify.com/authorize?response_type=code&client_id=%s&scope=%s&redirect_uri=%s&state=%s",
                clientId, scope, redirectUri, state);
        Map<String, Object> result = new HashMap<>();
        result.put("oauthUrl", oAuthUrl);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam String code,
            @RequestParam String state) throws ApiException {
        User user = userService.getUserByHashCode(state);
        if (user == null || !user.getHashCode().equals(state)) {
            throw new IllegalArgumentException("Invalid state parameter");
        }
        Map<String, Object> result = connector.getUserRefreshToken(code, state);
        user.setRefreshToken((String) result.get("refresh_token"));
        userService.updateUser(user);
        if (result.containsKey("error")) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
