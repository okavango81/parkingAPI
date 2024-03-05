package com.okavango.parkingapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ExceptionObject handlerDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        String message = "This username is already registered in the database";
        ExceptionObject exceptionObject = new ExceptionObject(request, LocalDateTime.now(), HttpStatus.CONFLICT.value(), message);
        return exceptionObject;
    }

    //recurso nao encontrado
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ExceptionObject handlerNotFoundException(NoSuchElementException ex, HttpServletRequest request){
        String message = "Resource Not Found";
        ExceptionObject exceptionObject = new ExceptionObject(request,LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), message);
        return exceptionObject;
    }

    //campos com formatacao incorreta
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionObject handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        String message = "Validation of the supplied argument(s) failed";
        List<String> errors =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                        .toList();

        ExceptionObject exceptionObject = new ExceptionObject(request, LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), message, errors);
        return exceptionObject;
    }

    //nova senha diferente da senha de confirmacao
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NewPasswordDifferentFromPasswordConfirmationException.class)
    public ExceptionObject handlerNewPasswordDifferentFromPasswordConfirmation(RuntimeException ex, HttpServletRequest request){
        String message = "The new password must be the same as the confirmation password";
        ExceptionObject exceptionObject = new ExceptionObject(request,LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message);
        return exceptionObject;
    }

    //senha fornecida diferente da senha cadastrada
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordProvidedDifferentFromRegisteredPasswordException.class)
    public ExceptionObject handlerPasswordProvidedDifferentFromRegisteredPassword(RuntimeException ex, HttpServletRequest request){
        String message = "Incorrect current password";
        ExceptionObject exceptionObject = new ExceptionObject(request,LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message);
        return exceptionObject;
    }

}// fim da classe
