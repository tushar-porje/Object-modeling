package com.crio.jukebox.exceptions;

public class UserDoNottHavePlayListException extends RuntimeException{
    public UserDoNottHavePlayListException(){
        super();
    }
     public UserDoNottHavePlayListException(String msg){
        super(msg);
     }
}
