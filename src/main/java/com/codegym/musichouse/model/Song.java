package com.codegym.musichouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String avatarUrl;

    @NotBlank
    @Size(min = 3, max = 60)
    private String nameSong;

    @NotBlank
    @Size(min = 3, max = 60)
    private String nameSinger;

    private String mp3Url;

    @Column(columnDefinition = "long")
    private String describes;

    @ManyToOne
    @JoinColumn
    private User user;

    public Song(){}

    public Song(String avatarUrl, String nameSong, String nameSinger, String mp3Url, String describe, User user) {
        this.avatarUrl = avatarUrl;
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.mp3Url = mp3Url;
        this.describes = describe;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getDescribe() {
        return describes;
    }

    public void setDescribe(String describe) {
        this.describes = describe;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }
}
