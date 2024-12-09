package com.understanding.spring.data.spring_data.understanding.scopes;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonUser {

    @PostConstruct
    public void init(){
        System.out.println("Im a singleton");
    }
}
