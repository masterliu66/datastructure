package com.example.datastructure.design_pattern;

/**
 * className Proxy
 * packageName com.example.datastructure.design_pattern
 * description
 *
 * @author CCC
 * @date 2020/11/17 23:45
 */
public class Proxy implements Subject {

    private Subject subject;

    public Proxy() {
        this.subject = new RealSubject();
    }

    @Override
    public void request() {
        preRequest();
        subject.request();
        postRequest();
    }

    private void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    private void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }

}
