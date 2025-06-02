package com.spotify.rewrapped.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spotify.rewrapped.connections.SpotifyConnection;
import com.spotify.rewrapped.exceptions.ApiException;
import com.spotify.rewrapped.models.User;

@Service
public class AuthenticationService {

  private final SpotifyConnection connection;
  private final UserService userService;
  private final SpotifyUserService spotifyUserService;

  public AuthenticationService(SpotifyConnection connection, UserService userService,
      SpotifyUserService spotifyUserService) {
    this.connection = connection;
    this.userService = userService;
    this.spotifyUserService = spotifyUserService;
  }

  public Map<String, Object> login() throws ApiException {
    String clientId = connection.getClientId();
    String scope = "user-read-private user-top-read user-read-email";
    String state = "myState";
    String redirectUri = connection.getRedirectURI();
    String oAuthUrl = String.format(
        "https://accounts.spotify.com/authorize?response_type=code&client_id=%s&scope=%s&redirect_uri=%s&state=%s",
        clientId, scope, redirectUri, state);
    Map<String, Object> result = new HashMap<>();
    result.put("oauthUrl", oAuthUrl);
    return result;
  }

  public Map<String, Object> callback(String code, String state) throws ApiException {
    Map<String, Object> result = connection.getUserRefreshToken(code, state);
    Map<String, Object> userInfo = spotifyUserService.getUserInfo((String) result.get("access_token"));
    String userSpotifyEmail = (String) userInfo.get("email");
    if (userService.getUserByEmail(userSpotifyEmail) == null) {
      User user = new User();
      user.setRefreshToken((String) result.get("refresh_token"));
      user.setName((String) userInfo.get("display_name"));
      user.setEmail(userSpotifyEmail);
      userService.createUser(user);
    }
    result.put("email", userSpotifyEmail);
    return result;
  }

}
