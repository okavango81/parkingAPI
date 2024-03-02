package com.okavango.parkingapi.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // classe para o objeto de excecao
    @Getter
    private static class ObjectException {
        private LocalDateTime moment;
        private int status;
        private String message;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> errors;

        public ObjectException(LocalDateTime moment, int status, String message) {
            this.moment = moment;
            this.status = status;
            this.message = message;
        }
    } // classe para o objeto de excecao

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Object> handlerDataIntegrityViolationException(DatabaseException ex) {

        LocalDateTime moment = LocalDateTime.now();
        String message = ex.getMessage();
        ObjectException objectException = new ObjectException(moment, HttpStatus.CONFLICT.value(), message);

        return ResponseEntity.status(objectException.getStatus()).body(objectException);
    }


}
