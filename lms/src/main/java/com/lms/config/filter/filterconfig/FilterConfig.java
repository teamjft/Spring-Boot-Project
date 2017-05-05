package com.lms.config.filter.filterconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.lms.config.filter.CustomXssFilter;

/**
 * Created by bhushan on 5/5/17.
 */
@Component
@DependsOn("xssFiltersConfiguration")
public class FilterConfig {
    @Autowired
    private XssFiltersConfiguration xssFiltersConfiguration;

    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CustomXssFilter());
        String patters[] = xssFiltersConfiguration.xssMatches();
        if(patters.length > 0) {
            registration.addUrlPatterns(patters);
        } else {
            registration.setEnabled(false);
        }
        return registration;
    }
}
