package com.codegym.musichouse.service;

import com.codegym.musichouse.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
}
