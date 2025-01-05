package com.spotify.rewrapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rewrapped.models.UserTopArtists;
import com.spotify.rewrapped.models.ids.UserTopArtistsId;

public interface UserTopArtistsRepository extends JpaRepository<UserTopArtists, UserTopArtistsId> {

}
