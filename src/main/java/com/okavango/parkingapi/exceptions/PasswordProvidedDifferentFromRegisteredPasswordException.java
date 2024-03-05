package com.okavango.parkingapi.exceptions;

public class PasswordProvidedDifferentFromRegisteredPasswordException extends RuntimeException{
    public PasswordProvidedDifferentFromRegisteredPasswordException(String message){
        super(message);
    }
}
