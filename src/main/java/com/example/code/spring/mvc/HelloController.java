package com.example.code.spring.mvc;

import com.example.code.spring.async.service.AService;
import com.example.code.spring.ioc.service.ScopeTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    ScopeTestService scopeTestService;

    @Autowired
    AService aService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/scope")
    public String scopeObj() {
        return scopeTestService.toString();
    }

    @GetMapping("/test")
    public String test() {
        aService.test();
        return "Success";
    }

}
