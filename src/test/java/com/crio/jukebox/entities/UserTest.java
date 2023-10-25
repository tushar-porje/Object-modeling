package com.crio.jukebox.entities;

import java.util.*;
import com.crio.jukebox.exceptions.UserDoNottHavePlayListException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("usertests")
public class UserTest {

    @Test
    @DisplayName("if user doesnt have playlist trow exception")
    public void User_invalide_deletePlayList_operation(){
        //arrange
        User user=new User(1, "Tushar", new ArrayList<>(Arrays.asList(1, 2, 3, 4)));

        PlayList playList=new PlayList(5, "abc", 1,new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        //act
        Assertions.assertThrows(UserDoNottHavePlayListException.class, ()->user.deletePlayList(playList));
        
    } 
}
