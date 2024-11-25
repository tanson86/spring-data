package com.understanding.spring.data.spring_data.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Service1 {

    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT)
    public void test(){

    }
}
