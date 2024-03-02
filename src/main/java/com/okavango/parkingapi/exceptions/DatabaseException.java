package com.okavango.parkingapi.exceptions;

public class DatabaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DatabaseException(Long id, String message){
        super(message);
    }

    public DatabaseException(String username, String message){
        super(message);
    }
}
