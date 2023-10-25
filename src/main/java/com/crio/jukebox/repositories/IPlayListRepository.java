package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.SongNotInPlayListException;

public interface IPlayListRepository extends CRUDRepository<PlayList,Integer>{
    PlayList storeSongInPlayList(Integer playListId,List<Integer> songs);
    PlayList removeSongFromPlayList(Integer playListId,List<Integer> songs) throws SongNotInPlayListException;
}
