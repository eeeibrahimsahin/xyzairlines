package com.eeeibra.xyzairlines.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/airplane").setViewName("airplane");
        registry.addViewController("/airplane_create").setViewName("airplane_create");
        registry.addViewController("/airport").setViewName("airport");
        registry.addViewController("/airport_create").setViewName("airport_create");
        registry.addViewController("/set_flight").setViewName("set_flight");
    }
}
