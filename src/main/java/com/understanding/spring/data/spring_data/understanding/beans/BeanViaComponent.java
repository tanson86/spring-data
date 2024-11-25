package com.understanding.spring.data.spring_data.understanding.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanViaComponent {
    String name;
    String age;

    @Autowired
    Order order;

    public BeanViaComponent(){
        System.out.println("Im the constructor");
    }

    @PostConstruct
    public void initialise(){
        System.out.println("Im from post construct and this comes after initialising order bean ie lazily created");
    }

    @PreDestroy
    public void destory(){
        System.out.println("Im from pre destroy");
    }
}
