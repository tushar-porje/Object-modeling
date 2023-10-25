package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity{
    private final String songName;
    private final String genre;
    private final String album;
    private final String artist;
    private final List<String> featuredArtists;

    public Song(Integer id,String songName, String genre, String artist, String album,
            List<String> featuredArtists) {
                this(songName,genre,artist,album,featuredArtists);
                this.id=id;
    }
    public Song(String songName, String genre, String artist, String album,
            List<String> featuredArtists) {
        this.songName = songName;
        this.genre = genre;
        this.artist = artist;
        this.album = album;
        this.featuredArtists = featuredArtists;
    }
    
    public Integer getId(){
        return this.id;
    }
    public String getSongName() {
        return this.songName;
    }
    public String getGenre() {
        return this.genre;
    }
    public String getArtist() {
        return this.artist;
    }
    public String getAlbum() {
        return this.album;
    }
    public List<String> getFeaturedArtists() {
        return this.featuredArtists;
    }
    

    @Override
    public String toString() {
        return "Song [id=" + id + ", album=" + album + ", artist=" + artist + ", featuredArtists="
                + featuredArtists + ", genre=" + genre + ", songName=" + songName + "]";
    }
}
