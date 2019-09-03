package com.codegym.musichouse.security.services;

import com.codegym.musichouse.model.Song;

import java.util.List;

public interface SongService {

    List<Song> findAll();

    List<Song> findByUserId(long userId);

    Song findById(Long id);

    void createHouse(Song song);

    void updateHouse(Song song);

    void deleteHouse(Long id);
}
