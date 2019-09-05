package com.codegym.musichouse.security.services.impl;

import com.codegym.musichouse.model.Playlist;
import com.codegym.musichouse.model.Song;
import com.codegym.musichouse.repository.PlaylistRepository;
import com.codegym.musichouse.security.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist findById(Long id) {
        return playlistRepository.findById(id).get();
    }

    @Override
    public void createPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }
}
