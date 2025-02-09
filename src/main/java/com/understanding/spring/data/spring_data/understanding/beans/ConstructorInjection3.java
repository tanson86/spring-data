package com.understanding.spring.data.spring_data.understanding.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ConstructorInjection3 {
    public ConstructorInjection3(){
        System.out.println("ConstructorInjection3 constructor");
    }
}
