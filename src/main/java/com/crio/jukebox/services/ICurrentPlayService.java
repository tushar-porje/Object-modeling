package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.*;

public interface ICurrentPlayService{
    Song playPlayList(Integer userId,Integer playListId) throws EmptyPlayListException;
    Song playSong(Integer userId,String nextOrBack);
    Song playSong(Integer userid,Integer songId) throws SongNotFoundException;
}