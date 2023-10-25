package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.*;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;


public class PlayListService implements IPlayListService{

    private final ISongRepository songRepository;
    private final IPlayListRepository playListRepository;
    private final IUserRepository userRepository;
    public PlayListService(ISongRepository songRepository,IPlayListRepository playListRepository,IUserRepository userRepository) {
        this.playListRepository=playListRepository;
        this.songRepository=songRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Integer createPlayList(Integer userId, String playListName, List<Integer> songs) throws UserNotFoundException ,SongNotFoundException{
        // System.out.println(userId+" "+playListName+" "+songs);
        userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("user of id "+userId+ " not present"));
        // System.out.println(user);
        for(Integer songId:songs){
            if(!songRepository.existsById(songId)){
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
        }
        PlayList playList=new PlayList(playListName, userId, songs);
        // System.out.println(playList+"++++++");
        //updating playlist repo
        PlayList repoPlayList=playListRepository.save(playList);
        // System.out.println(repoPlayList+"=====");
        Integer playListId=repoPlayList.getId();
        // System.out.println(playListId);
        //updating user repo
        // user.addPlayList(playList);
        // userRepository.save(user);
        userRepository.addPlayList(userId,playListId);
        // System.out.println(playListRepository.count()); 

        return playListId;
    }

    @Override
    public void deletePlayList(Integer userId, Integer playListId) {
        userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("user of id "+userId+ " not present"));
        playListRepository.findById(playListId).orElseThrow(()->new PlayListNotFoundException("Playlist Not Found"));
        userRepository.userHasPlayList(userId, playListId).orElseThrow(()->new PlayListNotFoundException("user doesn't have given playlist"));
        playListRepository.deleteById(playListId);
        // user.deletePlayList(playList);
        // userRepository.save(user);
        userRepository.removePlayList(userId,playListId);
        // System.out.println(playListRepository.count());
    }

    @Override
    public PlayList addSongInPlayList(Integer userId, Integer playListId, List<Integer> songs) {
        validateInput(userId,playListId,songs);
        return playListRepository.storeSongInPlayList(playListId,songs);
    }

    @Override
    public PlayList deleteSongFromPlayList(Integer userId, Integer playListId, List<Integer> songs) {
        validateInput(userId,playListId,songs);
        return playListRepository.removeSongFromPlayList(playListId, songs);
    }
    
    //to validate that user,songs and playlist are present in there respected maps and user has a playlist
    void validateInput(Integer userId, Integer playListId, List<Integer> songs){
        userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found"));
        playListRepository.findById(playListId).orElseThrow(()->new PlayListNotFoundException("Playlist Not Found"));
        userRepository.userHasPlayList(userId, playListId).orElseThrow(()->new PlayListNotFoundException("user doesn't have given playlist"));
        for(Integer songId:songs){
            if(songRepository.existsById(songId)){
                continue;
            }else{
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
        }
    }
    
}
