package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.services.IPlayListService;

public class CreatePlayListCommand implements ICommand{

    private final IPlayListService playListService;
    public CreatePlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }
    @Override
    public void execute(List<String> tokens) {
        // System.out.println(tokens+"===============================");
        try {
            Integer userId=Integer.parseInt(tokens.get(1));
            // System.out.print(userId);
            String playListName=tokens.get(2);
            // System.out.print(playListName);
            List<Integer> songIds=new ArrayList<>();
            // int i=3;
            // int size=tokens.size();
            // while(i<=size){
            //     Integer id=Integer.parseInt(tokens.get(i));
            //     System.out.print(id);
            //     songIds.add(id);
            //     i++;
            // }
            for (String token : tokens.subList(3, tokens.size())) {
                Integer songId = Integer.parseInt(token);
                songIds.add(songId);
            }
            // System.out.println(songIds);
            // System.out.println(userId+" "+playListName+" "+songIds);
            Integer playListId = playListService.createPlayList(userId, playListName, songIds);
            System.out.println("Playlist ID - "+playListId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
