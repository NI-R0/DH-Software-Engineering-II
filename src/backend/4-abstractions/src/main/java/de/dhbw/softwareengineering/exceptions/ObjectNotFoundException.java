package de.dhbw.softwareengineering.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String errorMessage){
        super(errorMessage);
    }

    public ObjectNotFoundException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }

}
