package com.example.code.spring.async.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AService {

    @Autowired
    BService bService;

    @Async
    public void test() {
        bService.test();
    }

}
