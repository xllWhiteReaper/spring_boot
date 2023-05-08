package com.first_spring_project.first_spring_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first_spring_project.first_spring_project.models.User;
import com.first_spring_project.first_spring_project.repositories.UserRepository;

@RestController()
public class UserController {

    @Autowired 
    private UserRepository userRepository;

    @RequestMapping(value = "users/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.getUserById(id);
    }

    @RequestMapping(value = "users")
    public User[] getUsers() {
        return userRepository.getAllUsers();
    }

    @RequestMapping(value = "fruits")
    public String[] getFruits() {
        final String[] fruits = { "Apple", "Banana", "Tangerine" };
        return fruits;
    }
}
