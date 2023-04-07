package com.example.code.spring.ioc;

import com.example.code.spring.ioc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * className SpringIocDemo
 * packageName com.example.code.spring.ioc
 * description
 *
 * @author CCC
 * @date 2020/11/30 23:37
 */
public class SpringIocDemo {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc_application.xml");
        UserService userService = context.getBean(UserService.class);
        userService.hello();
    }

}
