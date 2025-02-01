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
    public ResponseEntity<Map<String, Object>> login() throws ApiException {
        String clientId = connector.getClientId();
        String scope = "user-read-private user-top-read user-read-email";
        String state = "myState";
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
        if (!state.equals("myState")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, Object> result = connector.getUserRefreshToken(code, state);
        Map<String, Object> userInfo = userService.getUserInfo((String) result.get("access_token"));
        String userSpotifyEmail = (String) userInfo.get("email");
        if (userService.getUserByEmail(userSpotifyEmail) == null) {
            User user = new User();
            user.setRefreshToken((String) result.get("refresh_token"));
            user.setName((String) userInfo.get("display_name"));
            user.setEmail(userSpotifyEmail);
            userService.createUser(user);
        }
        result.put("email", userSpotifyEmail);
        if (result.containsKey("error")) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
