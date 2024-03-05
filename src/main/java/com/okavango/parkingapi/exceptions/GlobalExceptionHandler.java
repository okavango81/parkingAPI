package com.okavango.parkingapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //username ja cadastrado no BD
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionObject handlerDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        String message = "This username is already registered in the database";
        return new ExceptionObject(request, LocalDateTime.now(), HttpStatus.CONFLICT.value(), message);
    }

    //campos com formatacao incorreta
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionObject handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        String message = "Validation of the supplied argument(s) failed";
        List<String> errors =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                        .toList();

        return new ExceptionObject(request, LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), message, errors);
    }

    //recurso nao encontrado
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionObject handlerNotFoundException(NoSuchElementException ex, HttpServletRequest request){
        String message = "Resource Not Found";
        return new ExceptionObject(request, LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), message);
    }

    //nova senha diferente da senha de confirmacao
    @ExceptionHandler(NewPasswordDifferentFromPasswordConfirmationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionObject handlerNewPasswordDifferentFromPasswordConfirmation(RuntimeException ex, HttpServletRequest request){
        String message = "The new password must be the same as the confirmation password";
        return new ExceptionObject(request,LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message);
    }

    //senha fornecida diferente da senha cadastrada
    @ExceptionHandler(PasswordProvidedDifferentFromRegisteredPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionObject handlerPasswordProvidedDifferentFromRegisteredPassword(RuntimeException ex, HttpServletRequest request){
        String message = "Incorrect current password";
        return new ExceptionObject(request,LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message);
    }

}// fim da classe
