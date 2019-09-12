package com.codegym.musichouse.controller;

import com.codegym.musichouse.message.request.UpdatePlaylistForm;
import com.codegym.musichouse.message.respond.ResponseMessage;
import com.codegym.musichouse.model.Playlist;
import com.codegym.musichouse.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createPlaylist(@Valid @RequestBody Playlist playlist) {
//        Playlist playlist = new Playlist(
//                createPlaylistForm.getPlaylistName(),
//                createPlaylistForm.getUser(),
//                createPlaylistForm.getSongs()
//                );
        playlistService.save(playlist);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Create playlist successfully!", null), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllPlaylist() {
        List<Playlist> playlists = playlistService.findAll();
        return new ResponseEntity<>(playlists, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylist(@PathVariable("id") Long id) {
        Optional<Playlist> playlist = playlistService.findById(id);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePlaylist(@Valid @RequestBody UpdatePlaylistForm updatePlaylistForm, @PathVariable("id") Long id) {
        Playlist playlist = playlistService.findByIdPlaylist(id);
        playlist.setPlaylistName(updatePlaylistForm.getNamePlaylist());
        playlist.setSongs(updatePlaylistForm.getSongs());
        playlist.setUser(updatePlaylistForm.getUser());
        playlistService.save(playlist);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable("id") Long id) {
        playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
