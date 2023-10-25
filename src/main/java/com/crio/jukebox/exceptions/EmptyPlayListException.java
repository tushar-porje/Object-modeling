package com.crio.jukebox.exceptions;

public class EmptyPlayListException extends RuntimeException{
    public EmptyPlayListException(){
        super();
    }
     public EmptyPlayListException(String msg){
        super(msg);
     }
}
