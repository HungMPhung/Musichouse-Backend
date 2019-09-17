package com.codegym.musichouse.controller;

import com.codegym.musichouse.message.respond.ResponseMessage;
import com.codegym.musichouse.model.Singer;
import com.codegym.musichouse.security.services.UserPrinciple;
import com.codegym.musichouse.service.SingerService;
import com.codegym.musichouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/singers")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @Autowired
    private UserService userService;

    private UserPrinciple getCurrentUser(){
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseMessage> createSinger(@Valid @RequestBody Singer singer) {
        singer.setUser(this.userService.findById(getCurrentUser().getId()));
        singerService.save(singer);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("create singer successfully",null), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> allSinger(){
        List<Singer> singers = singerService.findAll();
        return new ResponseEntity<>(singers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdSinger(@PathVariable("id") Long id) {
        Singer singers = singerService.findByIdSinger(id);
        return new ResponseEntity<>(singers,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSinger(@PathVariable("id") Long id){
        singerService.delete(id);
        return new ResponseEntity<>(new ResponseMessage("delete success"),HttpStatus.OK);
    }
}
