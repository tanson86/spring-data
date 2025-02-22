package com.understanding.spring.data.spring_data.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachingService {

    public String notCache(){
        System.out.println("No cache");
        return "notcached";
    }

    @Cacheable("test")
    public String cached(){
        System.out.println("cached");
        return "cached";
    }
}
