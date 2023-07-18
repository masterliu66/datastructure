package com.example.code.spring.async.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.annotation.AbstractAsyncConfiguration;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.TaskManagementConfigUtils;

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyAsyncConfiguration extends AbstractAsyncConfiguration {

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        // @EnableAsync is not used
    }

    @Bean(name = TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public AsyncAnnotationBeanPostProcessor asyncAdvisor() {
        AsyncAnnotationBeanPostProcessor bpp = new CircleSupportAsyncAnnotationBeanPostProcessor();
        bpp.configure(this.executor, this.exceptionHandler);
        bpp.setProxyTargetClass(false);
        bpp.setOrder(Ordered.LOWEST_PRECEDENCE);
        return bpp;
    }

}
