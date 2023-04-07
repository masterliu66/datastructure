package com.example.datastructure.design_pattern;

/**
 * className TestDynamicProxy
 * packageName com.example.datastructure.design_pattern
 * description
 *
 * @author CCC
 * @date 2020/11/17 23:47
 */
public class TestDynamicProxy {

    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Subject proxySubject = (Subject) java.lang.reflect.Proxy.newProxyInstance(
                Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new DynamicProxyHandler(subject));
        proxySubject.request();
    }

}
