package com.crio.jukebox.exceptions;

public class SongNotInPlayListException extends RuntimeException {
    public SongNotInPlayListException()
    {
     super();
    }
    public SongNotInPlayListException(String msg)
    {
     super(msg);
    }
}
