package com.example.datastructure.controller;

import com.example.datastructure.entity.User;
import com.example.datastructure.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * className UserController
 * packageName com.example.datastructure.controller
 * description
 *
 * @author CCC
 * @date 2020/11/21 0:12
 */
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {

        userService.test();

        List<User> users = new ArrayList<>(1);

        users.add(new User(1, "test", "test", "test"));

        return users;
    }


}
