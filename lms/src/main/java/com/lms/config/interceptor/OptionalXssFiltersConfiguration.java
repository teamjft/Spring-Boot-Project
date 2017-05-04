package com.lms.config.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lms.utils.customannotation.annotaion.XxsFilter;

/**
 * Created by bhushan on 5/5/17.
 */
@Component
public class OptionalXssFiltersConfiguration implements Condition {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
     public static List<String> urls = new ArrayList<String >();
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods =
                this.requestMappingHandlerMapping.getHandlerMethods();

        for(Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            HandlerMethod method = item.getValue();
            for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
                if(method.hasMethodAnnotation(XxsFilter.class)) {
                    System.out.println(
                            method.getBeanType().getName() + "#" + method.getMethod().getName() +
                                    " <------------- " +urlPattern);
                    urls.add(urlPattern);
                }
            }
        }
        return urls.size() > 0;
    }
}
