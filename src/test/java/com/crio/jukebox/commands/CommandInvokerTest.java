package com.crio.jukebox.commands;



import static org.mockito.ArgumentMatchers.anyList;

import java.util.ArrayList;
import com.crio.jukebox.exceptions.NoSuchCommandException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@DisplayName("CommandInvokerTest")
@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {
    private CommandInvoker commandInvoker;

    @Mock
    CreateUserCommand createUserCommand;
    @Mock
    LoadDataCommand loadDataCommand;
    @Mock
    DeletePlayListCommand deletePlayListCommand;
    @Mock
    CreatePlayListCommand createPlayListCommand;
    @Mock
    PlayPlayListCommand playPlayListCommand;
    @Mock
    PlaySongCommand playSongCommand;
    @Mock
    modifyPlayListCommand modifyPlayListCommand;
    @BeforeEach
    void setup(){
        commandInvoker = new CommandInvoker();
        commandInvoker.register("LOAD-DATA",loadDataCommand);
        commandInvoker.register("CREATE_USER",createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlayListCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlayListCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST",playPlayListCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
    }

    @Test
    @DisplayName("executeCommand method Should Execute Command Given CommandName and List of tokens")
    public void executeCommand_GivenNameAndTokens_ShouldExecuteCommand() {
        commandInvoker.executeCommand("LOAD-DATA",anyList());
        commandInvoker.executeCommand("CREATE_USER",anyList());
        commandInvoker.executeCommand("CREATE-PLAYLIST",anyList());
        commandInvoker.executeCommand("DELETE-PLAYLIST",anyList());
        commandInvoker.executeCommand("MODIFY-PLAYLIST",anyList());
        commandInvoker.executeCommand("PLAY-PLAYLIST",anyList());
        commandInvoker.executeCommand("PLAY-SONG",anyList());
    }

    @Test
    @DisplayName("executeCommand method Should Throw Exception Given Incorrect CommandName and List of tokens")
    public void executeCommand_GivenIncorrectNameAndTokens_ShouldThrowException() {
        //Act and Assert
        Assertions.assertThrows(NoSuchCommandException.class,() -> commandInvoker.executeCommand("RANDOM-COMMAND",new ArrayList<String>()));

    }


}
