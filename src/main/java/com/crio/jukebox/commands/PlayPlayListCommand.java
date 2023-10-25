package com.crio.jukebox.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.ICurrentPlayService;

public class PlayPlayListCommand implements ICommand{

    private final ICurrentPlayService currentPlayService;
    public PlayPlayListCommand(ICurrentPlayService currentPlayService) {
        this.currentPlayService = currentPlayService;
    }
    @Override
    public void execute(List<String> tokens) {
        try { 
            Integer userId=Integer.parseInt(tokens.get(1));
            Integer playListId=Integer.parseInt(tokens.get(2));
            Song song = currentPlayService.playPlayList(userId, playListId);
            System.out.println("Current Song Playing");
            System.out.println("Song - "+song.getSongName());
            System.out.println("Album - "+song.getAlbum());
            System.out.println("Artists - "+song.getFeaturedArtists().stream().map(String::valueOf).collect(Collectors.joining(",")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
}
