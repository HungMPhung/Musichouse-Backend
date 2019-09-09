package com.codegym.musichouse.controller;

import com.codegym.musichouse.message.request.CreateSongForm;
import com.codegym.musichouse.message.respond.ResponseMessage;
import com.codegym.musichouse.model.Song;
import com.codegym.musichouse.security.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 21600000)
@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/create")
    public ResponseEntity<?> createSong(@Valid @RequestBody Song songRequest) {
//        Song song = new Song(
//                songRequest.getNameSong(),
//                songRequest.getSinger(),
//                songRequest.getCategory(),
//                songRequest.getLyrics(),
//                songRequest.getAvatarUrl(),
//                songRequest.getMp3Url(),
//                songRequest.getLikeSong(),
//                songRequest.getListenSong(),
//                songRequest.getDescribes()
//
//        );
        songService.save(songRequest);
        return new ResponseEntity<>(new ResponseMessage("Create Song successfully!",null), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAllSong() {
        List<Song> songs = this.songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSong(@PathVariable("id") Long id) {
        try {
            Song song = songService
                    .findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            song.setListenSong(song.getListenSong()+ 1);
            songService.save(song);
            return new ResponseEntity<>(song, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/by/{id}")
    public ResponseEntity<?> deleteSongById(@PathVariable("id") Long id) {
        songService.delete(id);
        return new ResponseEntity<>(new ResponseMessage("Delete Song successfully!"), HttpStatus.OK);
    }

}
