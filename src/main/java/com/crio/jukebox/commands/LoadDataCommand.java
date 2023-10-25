package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommand implements ICommand{

    private final ISongService songService;
    public LoadDataCommand(ISongService songService) {
        this.songService = songService;
    }
    @Override
    public void execute(List<String> tokens) {
        try {
            String songFilePath= tokens.get(1);
            songService.LoadSong(songFilePath);
            System.out.println("Songs Loaded successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
    
}
