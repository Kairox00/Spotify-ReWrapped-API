package com.spotify.rewrapped.connectors;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Map;
import com.spotify.rewrapped.utils.JsonParserUtil;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SpotifyConnector {
    private static SpotifyConnector instance;
    private String clientId;
    private String clientSecret;
    private String baseUrl;
    private WebClient client;
    private String redirectUri;

    private SpotifyConnector() {
        clientId = "6a6355fbeb044695930d74e002d91214";
        baseUrl = "https://api.spotify.com/v1/";
        clientSecret = "2722c709f97248889ba35a4f33069ced";
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        redirectUri = "http://localhost:5173/callback";
    }

    private String getApplicationToken() {
        String url = "https://accounts.spotify.com/api/token";
        String formData = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", clientId,
                clientSecret);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> parsedResponse = JsonParserUtil.parseJSON(response.body());
            return (String) parsedResponse.get("access_token");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Map<String, Object> getUserRefreshToken(String code, String state) {
        String url = "https://accounts.spotify.com/api/token";
        String formData = String.format("code=%s&redirect_uri=%s&grant_type=authorization_code", code,
                redirectUri);
        String credentials = clientId + ":" + clientSecret;
        String authorizationHeader = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", String.format("Basic %s", authorizationHeader))
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> parsedResponse = JsonParserUtil.parseJSON(response.body());
            return parsedResponse;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SpotifyConnector getInstance() {
        if (instance == null) {
            instance = new SpotifyConnector();
        }
        return instance;
    }

    public void initializeConnection() {
        String accessToken = getApplicationToken();
        client = client.mutate().defaultHeader("Authorization", "Bearer " + accessToken).build();
        System.out.println("Connected to Spotify");
    }

    public String getClientId() {
        return clientId;
    }

    public WebClient getClient() {
        return this.client;
    }

    public String getRedirectURI() {
        return this.redirectUri;
    }

}