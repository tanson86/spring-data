package com.understanding.spring.data.spring_data.understanding.scopes;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeUser {

    @PostConstruct
    public void postConstruct(){
        System.out.println("Im a protoype");
    }
}
