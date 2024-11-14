package com.understanding.spring.data.spring_data.understanding.error.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandledUsingResponseStatus {

    @GetMapping("/error/handled/resstatus")
    public void throwCustomException(){
        throw new CustomException2(HttpStatus.BAD_REQUEST, "Im a handled exception using @ResponseStatus");
    }
}
