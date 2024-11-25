package com.understanding.spring.data.spring_data.understanding.response.controller;

import com.understanding.spring.data.spring_data.understanding.interceptor.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @Autowired
    UtilClass utilClass;

    @GetMapping("/string1")
    public String getAsString(){
        System.out.println("Im returning a simple string");
        return "Im a String response";
    }
    @GetMapping("/string2")
    public ResponseEntity<String> getAsResponseEntityString(){
        utilClass.print();
        return new ResponseEntity<>("Im a ResponseEntity<String> response", HttpStatus.OK);
    }

    @GetMapping("/oldUrl")
    public ResponseEntity<Void> oldApi(){
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).
                header("Location","/newUrl").build();
    }
    @GetMapping("/newUrl")
    public ResponseEntity<String> newApi(){
        return new ResponseEntity("SUCCESSFULLL", HttpStatus.OK);
    }
}
