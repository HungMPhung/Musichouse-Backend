package com.codegym.musichouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String avatar;

    @NotBlank
    @Size(min = 3, max = 60)
    private String nameSong;

    @NotBlank
    @Size(min = 3, max = 60)
    private String nameSinger;

    private String url;

    @Column(columnDefinition = "long")
    private String describes;

    @ManyToOne
    @JoinColumn
    private User user;

    public Song(){}

    public Song(String avatar, String nameSong, String nameSinger, String url, String describe, User user) {
        this.avatar = avatar;
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.url = url;
        this.describes = describe;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
