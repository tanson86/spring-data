package com.understanding.spring.data.spring_data.understanding.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanViaConfiguration {
    @Bean
    public User user1(){
        return new User("Tanson",36);
    }

    @Bean
    public User user2(){
        return new User("Thomas",37);
    }
}
