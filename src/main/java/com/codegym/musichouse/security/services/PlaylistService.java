package com.codegym.musichouse.security.services;

import com.codegym.musichouse.model.Playlist;
import com.codegym.musichouse.model.Song;

import java.util.List;

public interface PlaylistService {

    List<Playlist> findAll();

    Playlist findById(Long id);

    void createPlaylist(Playlist playlist);

    void updatePlaylist(Playlist playlist);

    void deletePlaylist(Long id);
}
