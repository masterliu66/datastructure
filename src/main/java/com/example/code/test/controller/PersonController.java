package com.example.code.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.code.test.module.entity.Person;
import com.example.code.test.module.param.ArrayParam;
import com.example.code.test.module.param.PersonNewParam;
import com.example.code.test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/page")
    public Page<Person> page(@RequestParam int current, @RequestParam int limit) {
        return personService.page(current, limit);
    }

    @PostMapping
    public Person insert(@RequestBody PersonNewParam param) {
        return personService.insert(param.build());
    }

    @PostMapping("/batch")
    public List<Person> batchInsert(@RequestBody ArrayParam<Person, PersonNewParam> params) {
        return personService.batchInsert(params.build());
    }

    @GetMapping("")
    public Person select(@RequestParam(required = false) Long id) {
        return personService.selectOne(id);
    }

    @DeleteMapping("/{id}")
    public Person delete(@PathVariable long id) {
        return personService.delete(id);
    }

}
