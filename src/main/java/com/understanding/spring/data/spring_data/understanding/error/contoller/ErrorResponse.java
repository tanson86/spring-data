package com.understanding.spring.data.spring_data.understanding.error.contoller;

import java.time.LocalDateTime;

public class ErrorResponse {
    LocalDateTime localDateTime;
    int errorCode;
    String message;

    public ErrorResponse(int errorCode, String message) {
        this.localDateTime = LocalDateTime.now();
        this.errorCode = errorCode;
        this.message = message;
    }
}
