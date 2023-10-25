package com.crio.jukebox.appConfig;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.services.IPlayListService;

public class modifyPlayListAddSongCommand implements ICommand{

    private final IPlayListService playListService;
    public modifyPlayListAddSongCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }
    @Override
    public void execute(List<String> tokens) {
        try {
            Integer userId=Integer.parseInt(tokens.get(2));
            Integer playListId=Integer.parseInt(tokens.get(3));
            List<Integer> songIds=new ArrayList<>();
            // int i=4;
            // while(tokens.get(i)!=null){
            //     Integer id=Integer.parseInt(tokens.get(i));
            //     songIds.add(id);
            //     i++;
            // }
            for (String token : tokens.subList(4, tokens.size())) {
                Integer songId = Integer.parseInt(token);
                songIds.add(songId);
            }
            PlayList playList=playListService.addSongInPlayList(userId, playListId, songIds);
            
            System.out.println("Playlist ID - "+playList.getId());
            System.out.println("Playlist Name - "+playList.getPlayListName());
            System.out.println("Song IDs - "+playList.getSongs());

        } catch (Exception e) {
            e.getMessage();
        }
        
    }
    
    
}
