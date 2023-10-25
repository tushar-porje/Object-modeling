package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.*;

public interface IPlayListService{
    Integer createPlayList(Integer userId,String playListName,List<Integer> songs) throws SongNotFoundException;
    void deletePlayList(Integer userId, Integer playListId) throws PlayListNotFoundException;
    // void modifyPlayList();
    PlayList addSongInPlayList(Integer userId, Integer playListId,List<Integer> songs) throws SongNotFoundException;
    PlayList deleteSongFromPlayList(Integer userId, Integer playListId,List<Integer> songs) throws SongNotFoundException;
}