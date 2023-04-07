package com.example.datastructure.design_pattern;

/**
 * className TestCglibProxy
 * packageName com.example.datastructure.design_pattern
 * description
 *
 * @author CCC
 * @date 2020/11/17 23:53
 */
public class TestCglibProxy {

    public static void main(String[] args) {
        RealSubject subjectCglibProxy = (RealSubject) CglibProxy.getInstance(RealSubject.class);
        subjectCglibProxy.request();
    }

}
