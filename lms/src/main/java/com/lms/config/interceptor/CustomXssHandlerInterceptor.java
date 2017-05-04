package com.lms.config.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;

import com.lms.utils.customannotation.annotaion.XxsFilter;

/**
 * Created by bhushan on 4/5/17.
 */
public class CustomXssHandlerInterceptor implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new CaptureRequestWrapper((HttpServletRequest)servletRequest), servletResponse);
    }

    @Override
    public void destroy() {

    }
/*
    @Override
    public boolean preHandle(HttpServletRequest  request, HttpServletResponse response, Object handler) throws
            Exception {

        if (handler instanceof HandlerMethod) {
            System.out.println("Pre-handle");
            HandlerMethod handlerMethod=(HandlerMethod)handler;
            Method method=handlerMethod.getMethod();
            System.out.println("method.getDeclaringClass().isAnnotationPresent(Controller.class) "+method.getDeclaringClass().isAnnotationPresent(Controller.class) );
            System.out.println(" method.isAnnotationPresent(XxsFilter.class)"+ method.isAnnotationPresent(XxsFilter.class));
            if(method.getDeclaringClass().isAnnotationPresent(Controller.class) && method.isAnnotationPresent(XxsFilter.class)){
               CaptureRequestWrapper captureRequestWrapper = new CaptureRequestWrapper(request);
                super.preHandle(request, response, captureRequestWrapper);
            }
            return true;
        }
        return true;
    }*/
}
