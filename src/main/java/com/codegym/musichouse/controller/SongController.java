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
    public ResponseEntity<ResponseMessage> listAllHouse() {
        List<Song> songs = this.songService.findAll();

        if (songs.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage("Successfully but not found data",null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage("Successfully. Get list all house",songs),
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
                new ResponseMessage("Successfully. Get detail house",songs),
                HttpStatus.OK);
    }
}
