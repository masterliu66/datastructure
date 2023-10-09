package com.example.code.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;

/**
 * className Application
 * packageName com.example.code.spring
 * description
 *
 * @author CCC
 * @date 2020/12/20 22:06
 */
@SpringBootApplication(exclude = ElasticsearchRestClientAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
