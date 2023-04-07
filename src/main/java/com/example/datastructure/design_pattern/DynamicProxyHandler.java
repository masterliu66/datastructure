package com.example.datastructure.design_pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * className DynamicProxyHandler
 * packageName com.example.datastructure.design_pattern
 * description
 *
 * @author CCC
 * @date 2020/11/17 23:46
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object object;

    public DynamicProxyHandler(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        preRequest();
        Object result = method.invoke(object, args);
        postRequest();
        return result;
    }

    private void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    private void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }

}
