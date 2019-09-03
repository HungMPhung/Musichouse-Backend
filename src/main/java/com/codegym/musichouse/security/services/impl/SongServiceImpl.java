package com.codegym.musichouse.security.services.impl;

import com.codegym.musichouse.model.Song;
import com.codegym.musichouse.repository.SongRepository;
import com.codegym.musichouse.security.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findByUserId(long userId) {
        return songRepository.findByUserId(userId);
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).get();
    }

    @Override
    public void createHouse(Song song) {
        songRepository.save(song);
    }

    @Override
    public void updateHouse(Song song) {
        songRepository.save(song);
    }

    @Override
    public void deleteHouse(Long id) {
        songRepository.deleteById(id);
    }
}
