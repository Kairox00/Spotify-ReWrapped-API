package com.spotify.rewrapped;

import org.springframework.boot.ApplicationRunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import com.spotify.rewrapped.connectors.SpotifyConnector;

@Component
public class StartupApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Startup Running:");
        SpotifyConnector spotifyConnector = SpotifyConnector.getInstance();
        spotifyConnector.initializeConnection();
    }
}
