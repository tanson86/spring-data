package com.understanding.spring.data.spring_data.understanding.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {

    @GetMapping("/public")
    public String publicApi(){
        return "I am public";
    }

    @GetMapping("/token")
    public String publicApi(Principal principal){
        return JwtUtils.generateToken(principal.getName());
    }

    @GetMapping("/private")
    public String privateApi(){
        return "I am private";
    }

    @PostMapping("/logout")
    public void logout(){

    }
}
