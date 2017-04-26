package com.lms.config;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.lms.utils.converter.NotificationTypeConverter;
import com.lms.utils.converter.SaveImageServiceTypeConverter;
import com.lms.utils.enums.NotificationType;
import com.lms.utils.enums.SaveImageServiceType;

/**
 * Created by bhushan on 11/4/17.
 */
@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan(basePackages = {"com.lms"})
public class AppConfig extends WebMvcConfigurerAdapter {
    @Value("${localstroge.path}")
    private String filePath;

    @Bean
    protected ViewResolver InternalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/404").setViewName("404");
        registry.addViewController("/500").setViewName("500");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       super.addResourceHandlers(registry);
       registry .addResourceHandler("/opt/img/*")
                .addResourceLocations("file:/opt/img/");
    }

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        Map<Class<?>, Class<? extends PropertyEditor>> customEditors =
                new HashMap<Class<?>, Class<? extends PropertyEditor>>(1);
        customEditors.put(SaveImageServiceType.class, SaveImageServiceTypeConverter.class);
        customEditors.put(NotificationType.class, NotificationTypeConverter.class);
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setCustomEditors(customEditors);
        return configurer;
    }

}
