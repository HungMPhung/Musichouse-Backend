package com.codegym.musichouse.repository;

import com.codegym.musichouse.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Long> {
    List<Song> findByUserId (Long userId);
}
