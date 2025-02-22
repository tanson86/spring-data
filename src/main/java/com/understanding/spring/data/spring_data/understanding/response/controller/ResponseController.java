package com.understanding.spring.data.spring_data.understanding.response.controller;

import com.understanding.spring.data.spring_data.service.CachingService;
import com.understanding.spring.data.spring_data.understanding.interceptor.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ResponseController {

    @Autowired
    final UtilClass utilClass;

    @Autowired
    final CachingService cachingService;

    //public ResponseController(){}

    public ResponseController(UtilClass utilClass,CachingService cachingService){
        this.utilClass = utilClass;
        this.cachingService = cachingService;
    }

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

    @GetMapping("/testCache")
    public ResponseEntity<String> testCache(){
        for(int i=0;i<2;i++) {
            cachingService.notCache();
            cachingService.cached();
        }
        return new ResponseEntity("CACHE SERVICE TESTED", HttpStatus.OK);
    }
}
