package com.spotify.rewrapped.connectors;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import com.spotify.rewrapped.exceptions.ApiException;
import com.spotify.rewrapped.utils.JsonParserUtil;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SpotifyConnector {
    private String clientId;
    private String clientSecret;
    private String baseUrl;
    private WebClient client;
    private String redirectUri;

    @Value("${ui.url}")
    private String UI_URL;

    @Autowired
    private SpotifyConnector(Environment env) {
        clientId = "6a6355fbeb044695930d74e002d91214";
        baseUrl = "https://api.spotify.com/v1/";
        clientSecret = "2722c709f97248889ba35a4f33069ced";
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        redirectUri = env.getProperty("ui.url") + "/callback";
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

    public Map<String, Object> getUserRefreshToken(String code, String state) throws ApiException {
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
            throw new ApiException("Failed to authenticate with spotify", 500);
        }
    }

    public Map<String, Object> getNewAccessToken(String refreshToken) throws ApiException {
        String url = "https://accounts.spotify.com/api/token";
        String formData = String.format("refresh_token=%s&client_id=%s&grant_type=refresh_token",
                URLEncoder.encode(refreshToken, StandardCharsets.UTF_8),
                URLEncoder.encode(clientId, StandardCharsets.UTF_8));
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
            if (parsedResponse.containsKey("error")) {
                throw new Exception(parsedResponse.get("error").toString());
            }
            return parsedResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("Failed to authenticate with spotify", 500);
        }
    }

    @PostConstruct
    public void initializeConnection() {
        String accessToken = getApplicationToken();
        this.client = client.mutate().defaultHeader("Authorization", "Bearer " + accessToken).build();
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