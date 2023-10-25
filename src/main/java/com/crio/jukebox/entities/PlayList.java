package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.exceptions.SongNotInPlayListException;

public class PlayList extends BaseEntity{
    private final String playListName;
    private final Integer userId;
    private List<Integer> songs;

    public PlayList(PlayList playList){
        this(playList.id, playList.playListName,playList.userId,playList.songs);
    }
    public PlayList(Integer id, String playListName, Integer userId, List<Integer> songs) {
        this(playListName, userId, songs);
        this.id=id;
    }
    public PlayList(String playListName, Integer userId, List<Integer> songs) {
        this.playListName = playListName;
        this.userId = userId;
        this.songs = songs;
    }
    public PlayList(String playListName, Integer userId){
        this.playListName=playListName;
        this.userId=userId;
        this.songs=new ArrayList<>();
    }

    public String getPlayListName() {
        return playListName;
    }
    
    public Integer getUserId() {
        return userId;
    }
    public List<Integer> getSongs() {
        return songs;
    }
    
    public void addSong(Song song){
        songs.add(song.getId());
    }
    public void deleteSong(Song song) throws SongNotInPlayListException{
        boolean flag=false;
        for(int i=0;i<songs.size();i++){
            if(songs.get(i)==song.getId()){
                songs.remove(i);
                flag=true;
            }
        }
        if(flag==false){
            throw new SongNotInPlayListException("song is not present in the playlist");
        }
    }

    @Override
    public String toString() {
        return "PlayList [id=" + id + ", playListName=" + playListName + ", songs=" + songs + ", userId=" + userId
                + "]";
    }
}
