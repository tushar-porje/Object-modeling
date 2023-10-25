package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlayListService;

public class DeletePlayListCommand implements ICommand{

    private final IPlayListService playListService;
    public DeletePlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }
    @Override
    public void execute(List<String> tokens) {
        try {
            Integer userId=Integer.parseInt(tokens.get(1));
            Integer playListId=Integer.parseInt(tokens.get(2));
            playListService.deletePlayList(userId, playListId);

            System.out.println("Delete Successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
