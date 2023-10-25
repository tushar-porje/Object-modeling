package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository{

    private final Map<Integer,Song> songMap;
    
    private Integer autoIncrement = 0;

    public SongRepository(){
        songMap = new HashMap<Integer,Song>();
    }
    
    
    // @Override
    // public void saveAll(List<Song> songs){
    //     System.out.println(songs);
    //     for (Song song : songs) {
    //         if(!findByName(song.getSongName())){
    //             save(song);
    //         }
    //     }
    //     System.out.println(songMap);    
    // }

    @Override
    public Song save(Song entity) {
        // System.out.println(entity+"+++++");
        if( entity.getId() == null ){
            autoIncrement++;
            Song u = new Song(autoIncrement,entity.getSongName(),entity.getGenre(),entity.getArtist(),entity.getAlbum(),entity.getFeaturedArtists());
            songMap.put(u.getId(),u);
            return u;
        }
        songMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        return songMap.entrySet().stream().map(a -> a.getValue()).collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(Integer id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(Integer id) {
        return songMap.entrySet().stream().map(a -> a.getKey()).anyMatch(a->a.equals(id));
    }

    @Override
    public void delete(Song entity) {
        songMap.entrySet().removeIf(a -> a.getValue().equals(entity));     
    }

    @Override
    public void deleteById(Integer id) {
        songMap.remove(id);
    }

    @Override
    public long count() {
        return songMap.size();
    }

    @Override
    public boolean findByName(String songName) {
        return this.songMap.values().stream().allMatch(x -> x.getSongName().equals(songName));
    }

    
    
}
