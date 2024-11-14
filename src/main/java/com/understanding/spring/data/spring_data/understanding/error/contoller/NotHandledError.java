package com.understanding.spring.data.spring_data.understanding.error.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotHandledError {

    @GetMapping("/error/nothandled")
    public String throwError(){
        throw new RuntimeException("Testing runtime exception");
    }

    @GetMapping("/error/nothandled1")
    public ResponseEntity throwError1(){
        try {
            throw new RuntimeException("Testing runtime exception");
        }catch (RuntimeException re){
            ErrorResponse errorResponse = new ErrorResponse(500,re.getMessage());
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
