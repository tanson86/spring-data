package com.understanding.spring.data.spring_data.understanding.error.contoller;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpResponse;
import java.security.InvalidParameterException;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity handleError(InvalidParameterException ive){
        ErrorResponse er = new ErrorResponse(600, ive.getMessage());
        return new ResponseEntity(er, HttpStatus.BAD_REQUEST);
    }
}
