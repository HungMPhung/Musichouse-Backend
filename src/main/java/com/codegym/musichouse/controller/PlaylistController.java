package com.codegym.musichouse.controller;

import com.codegym.musichouse.message.respond.ResponseMessage;
import com.codegym.musichouse.model.Playlist;
import com.codegym.musichouse.security.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @RequestMapping(value = "/playlist", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> listAllPlaylist() {
        List<Playlist> playlists = this.playlistService.findAll();

        if (playlists.isEmpty()){
           return new ResponseEntity<ResponseMessage>(
                   new ResponseMessage("Successfully but not found data",null),
                   HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage("Successfully. Get list all Playlist",null),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/playlist/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> getPlaylist(@PathVariable Long id) {
        Playlist playlist = this.playlistService.findById(id);

        if (playlist == null){
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage("Successfully but not found data",null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage("Successfully. Get detail playlist",null),
                HttpStatus.OK);
    }
}
