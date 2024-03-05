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

    // classe para o objeto de excecao
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    private static class ObjectException {
        private String path;
        private String method;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime moment;
        private int status;
        private String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> errors;

        public ObjectException(HttpServletRequest request, LocalDateTime moment, int status, String message) {
            path = request.getRequestURI();
            method = request.getMethod();
            this.moment = moment;
            this.status = status;
            this.message = message;
        }
        public ObjectException(HttpServletRequest request, LocalDateTime moment, int status, String message, List<String> errors) {
            path = request.getRequestURI();
            method = request.getMethod();
            this.moment = moment;
            this.status = status;
            this.message = message;
            this.errors = errors;
        }

    } // classe para o objeto de excecao


    //username ja cadastrado no BD
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ObjectException handlerDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        String message = "This username is already registered in the database";
        ObjectException objectException = new ObjectException(request, LocalDateTime.now(), HttpStatus.CONFLICT.value(), message);
        return objectException;
    }

    //recurso nao encontrado
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ObjectException handlerNotFoundException(NoSuchElementException ex, HttpServletRequest request){
        String message = "Resource Not Found";
        ObjectException objectException = new ObjectException(request,LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), message);
        return objectException;
    }

    //campos com formatacao incorreta
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ObjectException handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        String message = "Validation of the supplied argument(s) failed";
        List<String> errors =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                        .toList();

        ObjectException objectException = new ObjectException(request, LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), message, errors);
        return objectException;
    }

    //nova senha diferente da senha de confirmacao
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NewPasswordDifferentFromPasswordConfirmation.class)
    public ObjectException handlerNewPasswordDifferentFromPasswordConfirmation(RuntimeException ex, HttpServletRequest request){
        String message = "The new password must be the same as the confirmation password";
        ObjectException objectException = new ObjectException(request,LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message);
        return objectException;
    }

    //senha fornecida diferente da senha cadastrada
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordProvidedDifferentFromRegisteredPassword.class)
    public ObjectException handlerPasswordProvidedDifferentFromRegisteredPassword(RuntimeException ex, HttpServletRequest request){
        String message = "Incorrect current password";
        ObjectException objectException = new ObjectException(request,LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message);
        return objectException;
    }




}
