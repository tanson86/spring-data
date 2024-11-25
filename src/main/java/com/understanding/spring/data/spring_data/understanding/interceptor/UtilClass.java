package com.understanding.spring.data.spring_data.understanding.interceptor;

import org.springframework.stereotype.Component;

@Component
public class UtilClass {

    @MyCustomAnnotation(name = "This is a util class")
    public void print(){
        System.out.println("Im from pring method");
    }
}
