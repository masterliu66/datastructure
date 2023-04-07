package com.example.datastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * className AopConfig
 * packageName com.example.datastructure
 * description
 *
 * @author CCC
 * @date 2020/11/21 0:00
 */
@Aspect
@Component
public class AopConfig {

    @Pointcut("execution(public * com.example.datastructure.service..*(..))")
    private void methodsToBeProfiled() {}

    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
    }

}
