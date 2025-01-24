package com.spotify.rewrapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rewrapped.models.UserTopArtist;
import com.spotify.rewrapped.models.ids.UserTopArtistId;

public interface UserTopArtistRepository extends JpaRepository<UserTopArtist, UserTopArtistId> {

}
