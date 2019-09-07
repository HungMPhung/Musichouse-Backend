package com.codegym.musichouse.security.services;

import com.codegym.musichouse.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<Song> findAll();

    List<Song> findByUserId(long userId);

    Optional<Song> findById(Long id);

    void createSong(Song song);

    void updateSong(Song song);

    void deleteSong(Long id);
}
