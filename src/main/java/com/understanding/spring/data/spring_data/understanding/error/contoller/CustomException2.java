package com.understanding.spring.data.spring_data.understanding.error.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomException2 extends RuntimeException{
    private HttpStatus httpStatus;

    public CustomException2(HttpStatus httpStatus,String message){
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
