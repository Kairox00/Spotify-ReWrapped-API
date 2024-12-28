package com.spotify.rewrapped;

import org.springframework.boot.ApplicationRunner;

import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import com.spotify.rewrapped.connectors.SpotifyConnector;
import com.spotify.rewrapped.services.ArtistService;
import com.spotify.rewrapped.services.UserService;

@Component
public class StartupApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Startup Running:");
        SpotifyConnector spotifyConnector = SpotifyConnector.getInstance();
        spotifyConnector.initializeConnection();
        UserService s = new UserService(spotifyConnector);
        // Map<String, String> res = s.getTopArtists(,"short_term");
        ArtistService a = new ArtistService(spotifyConnector);
        System.err.println(a.getArtistData("0TnOYISbd1XYRBk9myaseg"));

    }
}
