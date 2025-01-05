package com.spotify.rewrapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rewrapped.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
