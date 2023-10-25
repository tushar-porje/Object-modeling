package com.crio.jukebox.services;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;
import com.opencsv.CSVReader;

public class SongService implements ISongService{

    private final ISongRepository songRepository;
    // private List<Song> songs;

    
    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
        // songs=new ArrayList<>();
    }


    @Override
    public String LoadSong(String songFilePath) {  
        try(CSVReader reader = new CSVReader(new FileReader(songFilePath))) 
        {     
            String [] nextLine; 
            while ((nextLine = reader.readNext()) != null)  
            {  
                String songName=nextLine[1];
                String genre=nextLine[2];
                String albumName=nextLine[3];
                String albumArtist=nextLine[4];
                List<String> featuredArtists=Arrays.asList(nextLine[5].split("#"));
                Song song = new Song(songName, genre, albumArtist, albumName, featuredArtists);
                // songs.add(song);
                // System.out.println(songRepository.save(song)+"================"); 
                songRepository.save(song);
            } 
            // System.out.println(songRepository.findAll()+"+++");
            // System.out.println(songRepository.count());
            // songRepository.saveAll(songs);
            return "Songs Loaded successfully";
        }  
        catch (Exception e)   
        {  
        e.printStackTrace();
        return "error loading songs";
        }  
        
    }
    
}
