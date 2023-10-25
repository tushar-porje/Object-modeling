package com.crio.jukebox.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.ICurrentPlayService;

public class PlaySongCommand implements ICommand{

    private final ICurrentPlayService currentPlayService;
    public PlaySongCommand(ICurrentPlayService currentPlayService) {
        this.currentPlayService = currentPlayService;
    }
    
    @Override
    public void execute(List<String> tokens) {
        try {
            Song song;
            Integer userId=Integer.parseInt(tokens.get(1));
            switch (tokens.get(2)) {
                case "NEXT":
                    song=currentPlayService.playSong(userId, "NEXT");
                    System.out.println("Current Song Playing");
                    System.out.println("Song - "+song.getSongName());
                    System.out.println("Album - "+song.getAlbum());
                    System.out.println("Artists - "+song.getFeaturedArtists().stream().map(String::valueOf).collect(Collectors.joining(",")));
                    break;
                case "BACK":
                    song=currentPlayService.playSong(userId, "BACK");
                    System.out.println("Current Song Playing");
                    System.out.println("Song - "+song.getSongName());
                    System.out.println("Album - "+song.getAlbum());
                    System.out.println("Artists - "+song.getFeaturedArtists().stream().map(String::valueOf).collect(Collectors.joining(",")));
                    break;
                default:
                    // System.out.println("===playsong userid songid===");
                    song=currentPlayService.playSong(userId, Integer.parseInt(tokens.get(2)));
                    // System.out.println("===after calling service playsong userid songid===");
                    System.out.println("Current Song Playing");
                    System.out.println("Song - "+song.getSongName());
                    System.out.println("Album - "+song.getAlbum());
                    System.out.println("Artists - "+song.getFeaturedArtists().stream().map(String::valueOf).collect(Collectors.joining(",")));
            } 
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
             
    }
    
}
