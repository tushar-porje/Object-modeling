package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.services.IPlayListService;

public class modifyPlayListCommand implements ICommand{

    private final IPlayListService playListService;
    public modifyPlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(1).equals("ADD-SONG")){
            try {
                Integer userId=Integer.parseInt(tokens.get(2));
                Integer playListId=Integer.parseInt(tokens.get(3));
                List<Integer> songIds=new ArrayList<>();
                for (String token : tokens.subList(4, tokens.size())) {
                    Integer songId = Integer.parseInt(token);
                    songIds.add(songId);
                }
                PlayList playList=playListService.addSongInPlayList(userId, playListId, songIds);
                
                System.out.println("Playlist ID - "+playList.getId());
                System.out.println("Playlist Name - "+playList.getPlayListName());
                System.out.println("Song IDs - "+playList.getSongs().stream().map(String::valueOf).collect(Collectors.joining(" ")));
            } catch (Exception e) {
                e.getMessage();
            }
        }else if(tokens.get(1).equals("DELETE-SONG")){
            try {
                Integer userId=Integer.parseInt(tokens.get(2));
                Integer playListId=Integer.parseInt(tokens.get(3));
                List<Integer> songIds=new ArrayList<>();
                for (String token : tokens.subList(4, tokens.size())) {
                    Integer songId = Integer.parseInt(token);
                    songIds.add(songId);
                }
                PlayList playList=playListService.deleteSongFromPlayList(userId, playListId, songIds);
                
                System.out.println("Playlist ID - "+playList.getId());
                System.out.println("Playlist Name - "+playList.getPlayListName());
                System.out.println("Song IDs - "+playList.getSongs().stream().map(String::valueOf).collect(Collectors.joining(" ")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
}
