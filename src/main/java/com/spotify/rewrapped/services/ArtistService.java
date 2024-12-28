package com.spotify.rewrapped.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spotify.rewrapped.connectors.SpotifyConnector;

@Service
public class ArtistService {
    private WebClient client;

    @Autowired
    public ArtistService(SpotifyConnector connector){
        this.client = connector.getClient();
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
}
