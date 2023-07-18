package com.example.code.spring.async.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CircleSupportAsyncAnnotationBeanPostProcessor extends AsyncAnnotationBeanPostProcessor
        implements SmartInstantiationAwareBeanPostProcessor {

    private final Map<Object, Object> earlyProxyReferences = new ConcurrentHashMap<>(16);

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        this.earlyProxyReferences.put(beanName, bean);
        return super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean != null) {
            if (this.earlyProxyReferences.remove(beanName) != bean) {
                return super.postProcessAfterInitialization(bean, beanName);
            }
        }
        return bean;
    }
}
