package com.example.datastructure.design_pattern;

/**
 * className RealSubject
 * packageName com.example.datastructure.design_pattern
 * description
 *
 * @author CCC
 * @date 2020/11/17 23:44
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("访问真实主题方法...");
    }

}
