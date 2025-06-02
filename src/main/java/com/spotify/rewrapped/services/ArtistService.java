package com.spotify.rewrapped.services;

import org.springframework.stereotype.Service;

import com.spotify.rewrapped.connections.SpotifyConnection;
import com.spotify.rewrapped.dtos.ArtistDTO;
import com.spotify.rewrapped.dtos.attributes.AlbumsDTO;
import com.spotify.rewrapped.dtos.attributes.TopTracksDTO;

@Service
public class ArtistService {
    private final SpotifyConnection connection;

    public ArtistService(SpotifyConnection connection) {
        this.connection = connection;
    }

    public ArtistDTO getInfo(String id) {
        try {
            ArtistDTO response = connection.getClient().get().uri("artists/" + id).retrieve()
                    .bodyToMono(ArtistDTO.class).block();
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public TopTracksDTO getTopTracks(String id) {
        try {
            TopTracksDTO response = connection.getClient().get().uri("artists/" + id + "/top-tracks").retrieve()
                    .bodyToMono(TopTracksDTO.class).block();
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public AlbumsDTO getAlbums(String id) {
        try {
            AlbumsDTO response = connection.getClient().get().uri("artists/" + id + "/albums").retrieve()
                    .bodyToMono(AlbumsDTO.class).block();
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ArtistDTO getArtistData(String id) {
        ArtistDTO artist = getInfo(id);
        TopTracksDTO topTracks = getTopTracks(id);
        AlbumsDTO albums = getAlbums(id);
        artist.setAlbums(albums);
        artist.setTracks(topTracks.getTracks());
        return artist;
    }
}
