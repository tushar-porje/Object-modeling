package com.crio.jukebox.entities;

import java.util.*;
import com.crio.jukebox.exceptions.SongNotInPlayListException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("playlisttest")
public class PlayListTest {

    @Test
    @DisplayName("#1 PlayList should throw SongNotInPlayListException if song which we are tring to delete is not present in the playlist")
    public void playList_Invalid_deleteSong_Operation(){
        //Arrange
        Integer id=1;
        String playListName="tusharPlayList";
        Integer userId=1;
        List<Integer> songs = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        PlayList playList=new PlayList(id, playListName, userId, songs);
        Song song=new Song(6,"abc", "bcd", "def", "efg",null);

        //act amd assert
        Assertions.assertThrows(SongNotInPlayListException.class, ()->playList.deleteSong(song));
    }

    @Test
    @DisplayName("#2 Playlist should succesfully delete the song from playList")
    public void playList_SongSuccesfulldelete_Operation(){
        //Arrange
        Integer id=1;
        String playListName="tusharPlayList";
        Integer userId=1;
        List<Integer> songs = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        PlayList playList=new PlayList(id, playListName, userId, songs);
        Song song=new Song(2,"abc", "bcd", "def", "efg",null);

        playList.deleteSong(song);
        PlayList expectedPlayList=new PlayList(id, playListName, userId, new ArrayList<>(Arrays.asList(1, 3, 4)));
        //act amd assert
        Assertions.assertEquals(expectedPlayList.getSongs(), playList.getSongs());
    }
}
