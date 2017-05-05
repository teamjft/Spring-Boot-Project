package com.lms.config.filter.filterconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lms.utils.customannotation.annotaion.XxsFilter;

/**
 * Created by bhushan on 5/5/17.
 */
@Component
@DependsOn("requestMappingHandlerMapping")
public class XssFiltersConfiguration {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public String[] xssMatches() {
        List<String> urls = new ArrayList<String >();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods =
                this.requestMappingHandlerMapping.getHandlerMethods();

        for(Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            HandlerMethod method = item.getValue();
            for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
                if(method.hasMethodAnnotation(XxsFilter.class)) {
                    urls.add(urlPattern);
                }
            }
        }
        return urls.stream().toArray(String[]::new);
    }

}
