package com.understanding.spring.data.spring_data.understanding.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class MyCustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Im in do filter start");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Im in do filter end");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
