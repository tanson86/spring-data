package com.understanding.spring.data.spring_data.understanding.error.contoller;

import org.springframework.http.HttpStatus;

public class CustomExcpetion1 extends RuntimeException{
     private HttpStatus httpStatus;

     public CustomExcpetion1(HttpStatus httpStatus,String message){
         super(message);
         this.httpStatus = httpStatus;
     }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
