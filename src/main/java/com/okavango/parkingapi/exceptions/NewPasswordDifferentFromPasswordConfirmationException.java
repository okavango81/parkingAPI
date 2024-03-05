package com.okavango.parkingapi.exceptions;

public class NewPasswordDifferentFromPasswordConfirmationException extends RuntimeException{

    public NewPasswordDifferentFromPasswordConfirmationException(String message){
        super(message);
    }
}
