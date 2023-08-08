package com.example.code.spring.ioc.config;

import com.example.code.spring.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

    @Bean
    public User user() {
        return new User();
    }

}
