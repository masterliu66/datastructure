package com.example.code.test.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.code.test.module.entity.Person;
import com.example.code.test.repository.PersonRepository;
import com.example.code.test.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PersonService extends ServiceImpl<PersonRepository, Person> {

    @Autowired
    PersonRepository personRepository;

    @Value("${test.test}")
    String test;

    @PostConstruct
    public void init() {
        System.out.println(test);
    }

    public Page<Person> page(int current, int size) {
        return personRepository.selectPage(new Page<>(current, size), Wrappers.emptyWrapper());
    }

    public Person insert(Person person) {
        personRepository.insert(person);
        return person;
    }

    public List<Person> batchInsert(List<Person> persons) {
        saveBatch(persons);
        return persons;
    }

    public Person delete(long id) {

        Person person = select(id);

        Validator.validateNotNull(person, String.format("Resource not found for %d", id));

        personRepository.deleteById(id);

        return person;
    }

    public Person select(long id) {
        return personRepository.selectById(id);
    }

    public Person selectOne(Long id) {
        return personRepository.selectOne(id);
    }

}
