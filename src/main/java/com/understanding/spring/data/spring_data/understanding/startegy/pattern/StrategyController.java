package com.understanding.spring.data.spring_data.understanding.startegy.pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class StrategyController {

    private NotificationService notificationService;

    public StrategyController(NotificationService notificationService){
        this.notificationService=notificationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/strategy")
    public ResponseEntity<String> strategy(){
        notificationService.send("Tanson", NotificationType.EMAIL);
        return new ResponseEntity<>("Strategy response", HttpStatus.OK);
    }
}
