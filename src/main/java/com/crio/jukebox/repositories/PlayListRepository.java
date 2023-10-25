package com.crio.jukebox.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.SongNotInPlayListException;

public class PlayListRepository implements IPlayListRepository {

    private final Map<Integer,PlayList> PlayListMap;
    private Integer autoIncrement = 0;

    public PlayListRepository(){
        PlayListMap = new HashMap<Integer,PlayList>();
    }

    @Override
    public PlayList save(PlayList entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            PlayList u = new PlayList(autoIncrement,entity.getPlayListName(),entity.getUserId(),entity.getSongs());
            PlayListMap.put(u.getId(),u);
            return u;
        }
        PlayListMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<PlayList> findAll() {
        return PlayListMap.entrySet().stream().map(a -> a.getValue()).collect(Collectors.toList());
    }

    @Override
    public Optional<PlayList> findById(Integer id) {
        return Optional.ofNullable(PlayListMap.get(id));
    }

    @Override
    public boolean existsById(Integer id) {
        return PlayListMap.entrySet().stream().map(a -> a.getKey()).anyMatch(a->a.equals(id));
    }


    @Override
    public long count() {
        return PlayListMap.size();
    }

    @Override
    public void delete(PlayList entity) {
        PlayListMap.entrySet().removeIf(a -> a.getValue().equals(entity)); 
        
    }

    @Override
    public void deleteById(Integer id) {
        PlayListMap.remove(id);
        
    }

    @Override
    public PlayList storeSongInPlayList(Integer playListId, List<Integer> songs) {
        PlayList playList = PlayListMap.get(playListId);
        List<Integer> playListSongs=playList.getSongs();
        for (Integer songId : songs) {
            if (!playListSongs.contains(songId)) {
                playListSongs.add(songId);
            }
        }
        PlayList modifiedPlayList=new PlayList(playListId,playList.getPlayListName(), playList.getUserId(), playListSongs);
        PlayListMap.put(modifiedPlayList.getId(), modifiedPlayList);
        return modifiedPlayList;
    }

    @Override
    public PlayList removeSongFromPlayList(Integer playListId, List<Integer> songs) {
        PlayList playList = PlayListMap.get(playListId);
        List<Integer> playListSongs=playList.getSongs();
        for (Integer songId : songs) {
            if (!playListSongs.contains(songId)) {
                throw new SongNotInPlayListException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }
        for (Integer songId : songs) {
            playListSongs.remove(songId);
        }
        PlayList modifiedPlayList=new PlayList(playListId,playList.getPlayListName(), playList.getUserId(), playListSongs);
        PlayListMap.put(modifiedPlayList.getId(), modifiedPlayList);
        return modifiedPlayList;
    }
    
}
