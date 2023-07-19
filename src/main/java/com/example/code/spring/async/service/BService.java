package com.example.code.spring.async.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BService {

    @Autowired
    AService aService;

    public void test() {
        System.out.println("BService test");
    }

}
