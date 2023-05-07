package com.first_spring_project.first_spring_project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first_spring_project.first_spring_project.models.User;

@RestController()
public class UserController {

    @RequestMapping(value = "test")
    public String test() {
        return "<h2>This is a test</h2>";
    }

    @RequestMapping(value = "users")
    public User[] getUsers() {
        User[] users = {
                new User(
                        "cobra",
                        "github",
                        "fake1@gmail.com",
                        "123456789",
                        "definitelyNotMyDefaultPass2022"
                ),
                new User(
                        "xllWhiteReaper",
                        "github",
                        "fake2@gmail.com",
                        "987654321",
                        "definitelyNotMyDefaultPass"
                )
        };
        return users;
    }

    @RequestMapping(value = "fruits")
    public String[] getFruits() {
        final String[] fruits = { "Apple", "Banana", "Tangerine" };
        return fruits;
    }
}
