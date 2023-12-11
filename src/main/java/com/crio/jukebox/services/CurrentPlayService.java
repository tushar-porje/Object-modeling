package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.*;
// import com.crio.jukebox.repositories.CurrentPlayListRepository;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class CurrentPlayService implements ICurrentPlayService {

    private PlayList currentPlayList;
    private Integer currentSongIndex;
    private Integer currentUserId;

    private final IUserRepository userRepository;
    private final IPlayListRepository playListRepository;
    private final ISongRepository songRepository;
    // private final CurrentPlayListRepository currentPlayListRepository;



    public CurrentPlayService(IUserRepository userRepository,IPlayListRepository playListRepository,ISongRepository songRepository/* CurrentPlayListRepository currentPlayListRepository */) {
        this.currentPlayList = null;
        this.currentSongIndex = null;
        this.currentUserId=null;
        this.userRepository = userRepository;
        this.playListRepository = playListRepository;
        this.songRepository = songRepository;
        // this.currentPlayListRepository=currentPlayListRepository;
    }

    @Override
    public Song playPlayList(Integer userId, Integer playListId)throws EmptyPlayListException, UserNotFoundException, PlayListNotFoundException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        PlayList playList = playListRepository.findById(playListId).orElseThrow(() -> new PlayListNotFoundException("Playlist Not Found"));
        userRepository.userHasPlayList(userId, playListId).orElseThrow(() -> new PlayListNotFoundException("user doesn't have given playlist"));
        List<Integer> songs = playList.getSongs();
        Song song;
        if (songs.size() == 0) {
            throw new EmptyPlayListException("Playlist is empty.");
        } else {
            currentUserId = userId;
            currentPlayList = playListRepository.findById(playListId).get();
            currentSongIndex = 0;
            // currentSongIndex = CurrentPlayListRepository.storePlayList(playListId,0);
            song = songRepository.findById(playList.getSongs().get(currentSongIndex)).get();
        }
        return song;
    }

    @Override
    public Song playSong(Integer userId, String nextOrBack)throws UserNotFoundException, InvalidNEXT_BACKOperation {
        // TODO Auto-generated method stub
        Song songPlaying;
        if (userId != currentUserId)throw new UserNotFoundException("this user is not playing song currently");

        switch (nextOrBack) {
            case "NEXT":
                currentSongIndex = (currentSongIndex + 1) % currentPlayList.getSongs().size();
                // currentPlayListRepository.updateCurrentSong(currentPlayList.getId(),currentSongIndex);
                songPlaying = songRepository.findById(currentPlayList.getSongs().get(currentSongIndex)).get();
                break;
            case "BACK":
                currentSongIndex = (currentSongIndex - 1 + currentPlayList.getSongs().size())% currentPlayList.getSongs().size();
                // currentPlayListRepository.updateCurrentSong(currentPlayList.getId(),currentSongIndex);
                songPlaying = songRepository.findById(currentPlayList.getSongs().get(currentSongIndex)).get();
                break;
            default:
                throw new InvalidNEXT_BACKOperation("operation of PLAY-SONG command is wrong");
        }

        return songPlaying;
    }

    @Override
    public Song playSong(Integer userId, Integer songId) throws UserNotFoundException, SongNotInPlayListException {
        
        if (userId != currentUserId) {
            throw new UserNotFoundException("this user is not playing song currently");
        }
        if(!currentPlayList.getSongs().contains(songId)){
            throw new SongNotInPlayListException("Given song id is not a part of the active playlist");
        }
        

        currentSongIndex = currentPlayList.getSongs().indexOf(songId);
        // currentPlayListRepository.updateCurrentSong(currentPlayList.getId(),currentSongIndex);
        return songRepository.findById(songId).get();
    }



}
