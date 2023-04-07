package com.example.code.test.module.param;

import com.example.code.test.module.entity.Person;
import com.example.code.test.util.Validator;

public class PersonNewParam implements EntityNewParam<Person> {

    private String name;

    private Integer age;

    @Override
    public void init() {

    }

    @Override
    public void valid() {
//        Validator.validateNotNull(name, "Name must input");
        Validator.validateNotNull(age, "Age must input");
    }

    @Override
    public Person doBuild() {

        Person person = new Person();

        person.setName(name);
        person.setAge(age);

        return person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
