package com.codegym.musichouse.repository;

import com.codegym.musichouse.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song,Long> {
    List<Song> findByUserId (Long userId);
    Optional<Song> findByNameSongContaining(String song);
}
