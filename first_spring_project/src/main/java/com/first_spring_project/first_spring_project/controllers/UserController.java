package com.first_spring_project.first_spring_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first_spring_project.first_spring_project.dao.UserDao;
import com.first_spring_project.first_spring_project.models.User;
import com.first_spring_project.first_spring_project.repositories.UserRepository;

@RestController()
@RequestMapping("/api")
public class UserController {

    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "users/{id}")
    public User getUserById(@PathVariable String id) {
        // return userRepository.getUserById(id);
        return userDao.getUserById(id);
    }

    @RequestMapping(value = "users")
    public List<User> getUsers() {
        // return userRepository.getAllUsers();
        return userDao.getAllUsers();
    }

    @RequestMapping(value = "test")
    public String test() {
        // return userRepository.getAllUsers();
        return userDao.test();
    }

    @RequestMapping(value = "fruits")
    public String[] getFruits() {
        final String[] fruits = { "Apple", "Banana", "Tangerine" };
        return fruits;
    }
}
