package com.codegym.musichouse.repository;

import com.codegym.musichouse.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    Optional<Playlist> findAllByPlaylistNameContaining(String playlist_name);
    Optional<Playlist> findAllByUserUsername(String username);
}
