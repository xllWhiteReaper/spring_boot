package com.first_spring_project.first_spring_project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "test")
    public String test() {
        return "This is a test";
    }
}
