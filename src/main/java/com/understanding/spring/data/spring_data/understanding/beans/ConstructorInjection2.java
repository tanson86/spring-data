package com.understanding.spring.data.spring_data.understanding.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ConstructorInjection2 {
    public ConstructorInjection2(){
        System.out.println("ConstructorInjection2 constructor");
    }
}
