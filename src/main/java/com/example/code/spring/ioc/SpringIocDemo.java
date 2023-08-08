package com.example.code.spring.ioc;

import com.example.code.spring.bean.User;
import com.example.code.spring.ioc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

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
        xml();
        annotation();
    }

    private static void xml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc_application.xml");
        UserService userService = context.getBean(UserService.class);
        userService.hello();
    }

    private static void annotation() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("com.example.code.spring.ioc");
        context.refresh();
        UserService userService = context.getBean(UserService.class);
        userService.hello();
        User user = context.getBean(User.class);
        System.out.println(user);
    }

}
