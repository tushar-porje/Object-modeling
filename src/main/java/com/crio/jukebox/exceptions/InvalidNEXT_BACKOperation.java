package com.crio.jukebox.exceptions;

public class InvalidNEXT_BACKOperation extends RuntimeException{
    public InvalidNEXT_BACKOperation(){
        super();
    }
     public InvalidNEXT_BACKOperation(String msg){
        super(msg);
     }
}
