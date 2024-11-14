package com.understanding.spring.data.spring_data.understanding.error.contoller;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.security.InvalidParameterException;

@RestController
public class ErrorHandledUsingErrorHandler {

    @GetMapping("/error/handled1")
    public void throwCustomException(){
        throw new CustomExcpetion1(HttpStatus.BAD_REQUEST, "Im a handled exception using @ExceptionHandler");
    }

    @GetMapping("/error/handled2")
    public void throwCustomException1(){
        throw new InvalidParameterException("Im a handled exception using @ControllerAdvice");
    }

    @ExceptionHandler(CustomExcpetion1.class)
    public ResponseEntity handleError(CustomExcpetion1 ce, HttpResponse res, HttpRequest req){
        ErrorResponse er = new ErrorResponse(ce.getHttpStatus().value(), ce.getMessage());
        return new ResponseEntity(er,ce.getHttpStatus());
    }

    /***
     * Use below style if you want to pass multiple expcetions
     */
//    @ExceptionHandler({CustomExcpetion1.class, InvalidParameterException.class})
//    public ResponseEntity handleError(Exception ce, HttpResponse res, HttpRequest req){
//        ErrorResponse er = new ErrorResponse(605, ce.getMessage());
//        return new ResponseEntity(er,HttpStatus.BAD_REQUEST);
//    }
}
