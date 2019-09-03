package com.codegym.musichouse.controller;

import com.codegym.musichouse.message.respond.ResponseMessage;
import com.codegym.musichouse.model.Song;
import com.codegym.musichouse.security.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SongController {

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> listAllSong() {
        List<Song> songs = this.songService.findAll();

        if (songs.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage("Successfully but not found data",null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage("Successfully. Get list all songs",songs),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> getSong(@PathVariable Long id) {
        Song songs = this.songService.findById(id);

        if (songs == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage("Successfully but not found data",null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage("Successfully. Get detail songs",songs),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createSongs(@RequestBody Song songs) {
        this.songService.createSong(songs);

        if (songs == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage("Successfully but not found data", null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage("Successfully. Create songs", songs),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updateSong(@PathVariable Long id,@RequestBody Song songs){
        this.songService.updateSong(songs);

        if (songs == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage("Successfully but not found data", null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage("Successfully. update songs", songs),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteSong(@PathVariable Long id){
        this.songService.deleteSong(id);

        if (id == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage("Successfully but not found data", null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage("Successfully. delete songs", id),
                HttpStatus.OK);

    }

}
