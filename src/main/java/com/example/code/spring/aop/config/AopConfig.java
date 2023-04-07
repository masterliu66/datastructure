package com.example.code.spring.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * className AopConfig
 * packageName com.example.code.spring.aop.config
 * description
 *
 * @author CCC
 * @date 2021/1/22 20:46
 */
@Component
@EnableAspectJAutoProxy
@Aspect
public class AopConfig {

    @Pointcut("execution(public * com.example.code.spring.aop.service.*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(""+joinPoint.getSignature().getName()+"运行。。。@Before:参数列表是：{"+ Arrays.asList(args)+"}");
    }

}
