package com.understanding.spring.data.spring_data.understanding.beans;

import org.springframework.stereotype.Component;

@Component
public class ConstructorInjection1 {
    public ConstructorInjection1(ConstructorInjection2 constructorInjection2,ConstructorInjection3 constructorInjection3) {
        System.out.println("ConstructorInjection1 constructor");
    }
}
