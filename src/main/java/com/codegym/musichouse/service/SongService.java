package com.codegym.musichouse.service;

import com.codegym.musichouse.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SongService {

    List<Song> findAll();

    Optional<Song> findById(long id);

    Song save(Song song);

    void delete(long id);
}
