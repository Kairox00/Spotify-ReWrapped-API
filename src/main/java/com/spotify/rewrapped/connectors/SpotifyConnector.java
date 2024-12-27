package com.spotify.rewrapped.connectors;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spotify.rewrapped.utils.JsonParserUtil;
import org.springframework.web.reactive.function.client.WebClient;

public class SpotifyConnector {
    private static SpotifyConnector instance;
    private String clientId;
    private String clientSecret;
    private String baseUrl;
    private WebClient client;

    private SpotifyConnector(){
        clientId = "6a6355fbeb044695930d74e002d91214";
        baseUrl = "https://api.spotify.com/v1/";
        clientSecret = "2722c709f97248889ba35a4f33069ced";
        client = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
    }
    
    private String getNewToken(){
        String url = "https://accounts.spotify.com/api/token";
        String formData = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", clientId, clientSecret);
        HttpClient client =  HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, String> parsedResponse = JsonParserUtil.parseJSON(response.body());
            return parsedResponse.get("access_token");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public static SpotifyConnector getInstance(){
        if(instance == null){
            instance = new SpotifyConnector();
        }
        return instance;
    }

    public void initializeConnection(){
        String accessToken = getNewToken();
        client = client.mutate().defaultHeader("Authorization", "Bearer "+accessToken).build();
    }

    public Map<String, String> getArtistData(String id){
        try{
            Map<String, String> response = client.get().uri("artists/"+id).retrieve().bodyToMono(Map.class).block();
            return response;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Map<String, String> getTopArtists(String timeRange){
        try{
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("time_range", timeRange);
            Map<String, String> response = client.get().uri("me/top/artists").retrieve().bodyToMono(Map.class).block();
            return response;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }




}