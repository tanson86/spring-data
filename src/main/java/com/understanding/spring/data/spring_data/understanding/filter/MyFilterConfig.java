package com.understanding.spring.data.spring_data.understanding.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean<MyCustomFilter> myCustomFilter(){
        FilterRegistrationBean<MyCustomFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new MyCustomFilter());
        filter.addUrlPatterns("/string1");
        filter.setOrder(1);
        return filter;
    }
}
