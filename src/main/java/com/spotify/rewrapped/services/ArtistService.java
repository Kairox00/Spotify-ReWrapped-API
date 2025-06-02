package com.spotify.rewrapped.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spotify.rewrapped.connections.SpotifyConnection;

@Service
public class ArtistService {
    private final SpotifyConnection connection;

    public ArtistService(SpotifyConnection connection) {
        this.connection = connection;
    }

    public Map<String, Object> getInfo(String id) {
        try {
            Map<String, Object> response = connection.getClient().get().uri("artists/" + id).retrieve()
                    .bodyToMono(Map.class).block();
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Map<String, Object> getTopTracks(String id) {
        try {
            Map<String, Object> response = connection.getClient().get().uri("artists/" + id + "/top-tracks").retrieve()
                    .bodyToMono(Map.class).block();
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Map<String, Object> getAlbums(String id) {
        try {
            Map<String, Object> response = connection.getClient().get().uri("artists/" + id + "/albums").retrieve()
                    .bodyToMono(Map.class).block();
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Map<String, Object> getArtistData(String id) {
        Map<String, Object> info = getInfo(id);
        Map<String, Object> topTracks = getTopTracks(id);
        Map<String, Object> albums = getAlbums(id);
        Map<String, Object> result = new HashMap<>();
        result.putAll(info);
        result.put("albums", albums);
        result.putAll(topTracks);
        return result;
    }
}
