package com.example.code.spring.bean;

import com.example.datastructure.entity.Entity;

/**
 * className User
 * packageName com.example.code.spring.bean
 * description
 *
 * @author CCC
 * @date 2020/11/30 23:39
 */
public class User implements Entity {

    private Integer id;

    private String name;

    private String username;

    private String password;

    public User() {
        super();
    }

    public User(Integer id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
