package com.spotify.rewrapped.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spotify.rewrapped.connectors.SpotifyConnector;
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
    public String login(@RequestBody Map<String, Object> data) {
        String clientId = connector.getClientId();
        String scope = "user-read-private user-top-read user-read-email";
        String email = (String) data.get("email");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            user = userService.createUser(email);
        }
        String state = user.getHashCode();
        String redirectUri = connector.getRedirectURI();
        String oAuthUrl = String.format(
                "https://accounts.spotify.com/authorize?response_type=code&client_id=%s&scope=%s&redirect_uri=%s&state=%s",
                clientId, scope, redirectUri, state);
        return oAuthUrl;
    }

    @GetMapping("/callback")
    public Map<String, Object> callback(@RequestParam String code,
            @RequestParam String state) {
        User user = userService.getUserByHashCode(state);
        if (user == null || !user.getHashCode().equals(state)) {
            throw new IllegalArgumentException("Invalid state parameter");
        }
        Map<String, Object> result = connector.getUserRefreshToken(code, state);
        user.setRefreshToken((String) result.get("refresh_token"));
        userService.updateUser(user);
        return result;
    }
}
