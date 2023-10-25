package com.crio.jukebox.repositories;

// import java.util.List;
import com.crio.jukebox.entities.Song;

public interface ISongRepository extends CRUDRepository<Song,Integer>{
    // void saveAll(List<Song> songs);
    boolean findByName(String songName);
}
