package com.okavango.parkingapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ExceptionObject {

    private String path;
    private String method;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime moment;
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ExceptionObject(HttpServletRequest request, LocalDateTime moment, int status, String message) {
        path = request.getRequestURI();
        method = request.getMethod();
        this.moment = moment;
        this.status = status;
        this.message = message;
    }
    public ExceptionObject(HttpServletRequest request, LocalDateTime moment, int status, String message, List<String> errors) {
        path = request.getRequestURI();
        method = request.getMethod();
        this.moment = moment;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
