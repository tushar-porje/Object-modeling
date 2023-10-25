package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.CreatePlayListCommand;
import com.crio.jukebox.commands.DeletePlayListCommand;
// import com.crio.jukebox.repositories.CurrentPlayListRepository;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.CurrentPlayService;
import com.crio.jukebox.services.ICurrentPlayService;
import com.crio.jukebox.services.IPlayListService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlayListService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.commands.modifyPlayListCommand;

public class ApplicationConfig {
    private final IPlayListRepository playListRepository = new PlayListRepository();
    private final IUserRepository userRepository = new UserRepository();
    private final ISongRepository songRepository = new SongRepository();

    // private final CurrentPlayListRepository currentPlayListRepository=new CurrentPlayListRepository();

    private final IUserService userService = new UserService(userRepository);
    private final ICurrentPlayService currentPlayService = new CurrentPlayService(userRepository, playListRepository, songRepository/*,currentPlayListRepository */);
    private final IPlayListService playListService = new PlayListService(songRepository, playListRepository, userRepository);
    private final ISongService songService = new SongService(songRepository);
    
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songService);
    private final DeletePlayListCommand deletePlayListCommand = new DeletePlayListCommand(playListService);
    private final CreatePlayListCommand createPlayListCommand = new CreatePlayListCommand(playListService);
    private final PlayPlayListCommand playPlayListCommand = new PlayPlayListCommand(currentPlayService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(currentPlayService);
    // private final modifyPlayListAddSongCommand modifyPlayListAddSongCommand = new modifyPlayListAddSongCommand(playListService);
    // private final modifyPlayListDeleteSongCommand modifyPlayListDeleteSongCommand = new modifyPlayListDeleteSongCommand(playListService);
    private final modifyPlayListCommand modifyPlayListCommand=new modifyPlayListCommand(playListService);
    private static final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA",loadDataCommand);
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlayListCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlayListCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST",playPlayListCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        return commandInvoker;
    }
}
