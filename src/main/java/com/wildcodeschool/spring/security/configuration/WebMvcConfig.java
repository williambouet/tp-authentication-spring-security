package com.wildcodeschool.spring.security.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wildcodeschool.spring.security.persistence.repositories.UserRepository;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Autowired
    public WebMvcConfig(UserRepository userRepository) {
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("add view controllers");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/auth").setViewName("auth/auth");
        registry.addViewController("/auth/admin").setViewName("auth/admin");
        registry.addViewController("/auth/superadmin").setViewName("auth/superadmin");
        registry.addViewController("/errorAlreadyConnected").setViewName("/errorAlreadyConnected");
        registry.addViewController("/errorAccessUnAuthorised").setViewName("/errorAccessUnAuthorised");
    }
}