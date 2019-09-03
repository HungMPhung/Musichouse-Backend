package com.codegym.musichouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 60)
    private String nameSong;

    @NotBlank
    @Size(min = 3, max = 60)
    private String nameSinger;

    private String url;

    private String avatar;

    @Column(columnDefinition = "long")
    private String description;

    @ManyToOne
    @JoinColumn
    private User user;

    public Song(){}

    public Song(@NotBlank @Size(min = 3, max = 60) String nameSong, @NotBlank @Size(min = 3, max = 60) String nameSinger, String url, String avatar, String description) {
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.url = url;
        this.avatar = avatar;
        this.description = description;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
