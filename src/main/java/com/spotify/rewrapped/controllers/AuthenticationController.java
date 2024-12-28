package com.spotify.rewrapped.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spotify.rewrapped.connectors.SpotifyConnector;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private SpotifyConnector connector;

    @GetMapping("/login")
    public String login() {
        String clientId = connector.getClientId();
        String redirectUri = connector.getRedirectURI();
        String scope = "user-read-private user-top-read user-read-email";
        String state = "qwertyuiopasdfgh";
        String oAuthUrl = String.format(
                "https://accounts.spotify.com/authorize?response_type=code&client_id=%s&scope=%s&redirect_uri=%s&state=%s",
                clientId, scope, redirectUri, state);
        return oAuthUrl;
    }

    @GetMapping("/callback")
    public Map<String, Object> callback(@RequestParam String code, @RequestParam String state) {
        Map<String, Object> result = connector.getUserToken(code, state);
        return result;
    }
}
