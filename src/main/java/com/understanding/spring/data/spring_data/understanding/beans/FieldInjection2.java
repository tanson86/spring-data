package com.understanding.spring.data.spring_data.understanding.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class FieldInjection2 {
    public FieldInjection2(){
        System.out.println("FieldInjection2 constructor");
    }
}
