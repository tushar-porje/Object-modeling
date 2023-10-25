// package com.crio.jukebox.repositories;

// import java.util.HashMap;
// import java.util.Map;

// public class CurrentPlayListRepository implements ICurrentPlayListRepository {
//     private final Map<Integer, Integer> currentSongMap;

//     public CurrentPlayListRepository() {
//         this.currentSongMap = new HashMap<>();
//     }

//     @Override
//     public Integer storePlayList(Integer playListId, Integer SongId) {
//         if (currentSongMap.containsKey(playListId)) {
//             return getCurrentSongOfPlayList(playListId);
//         } else {
//             return SongId;
//         }
//     }

//     @Override
//     public Integer getCurrentSongOfPlayList(Integer PlayListId) {
//         return currentSongMap.get(PlayListId);
//     }

//     @Override
//     public Integer updateCurrentSong(Integer PlayListId, Integer songId) {
//         currentSongMap.put(PlayListId, songId);
//         return currentSongMap.get(PlayListId);
//     }

// }
