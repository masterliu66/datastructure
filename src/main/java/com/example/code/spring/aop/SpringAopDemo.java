package com.example.code.spring.aop;

import com.example.code.spring.aop.config.AopConfig;
import com.example.code.spring.aop.service.DomainService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * className SpringIocDemo
 * packageName com.example.code.spring.ioc
 * description
 *
 * @author CCC
 * @date 2020/11/30 23:37
 */
public class SpringAopDemo {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.code.spring.aop");
        DomainService domainService = context.getBean(DomainService.class);
        domainService.doSomething();
    }

}
