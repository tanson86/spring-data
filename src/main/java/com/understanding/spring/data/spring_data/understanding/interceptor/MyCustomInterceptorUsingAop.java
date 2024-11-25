package com.understanding.spring.data.spring_data.understanding.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class MyCustomInterceptorUsingAop {
    @Around("@annotation(com.understanding.spring.data.spring_data.understanding.interceptor.MyCustomAnnotation)")
    public void invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Do something before actual method advice via aop");
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        if(method.isAnnotationPresent(MyCustomAnnotation.class)){
            MyCustomAnnotation myCustomAnnotation = method.getAnnotation(MyCustomAnnotation.class);
            System.out.println(myCustomAnnotation.name());
        }
        joinPoint.proceed();
        System.out.println("Do something after actual method advice via aop");
    }
}
