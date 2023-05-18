package com.first_spring_project.first_spring_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.first_spring_project.first_spring_project.dao.UserDao;
import com.first_spring_project.first_spring_project.models.User;

@RestController()
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userDao.getUserById(id);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable Long id) {
        userDao.deleteUserById(id);
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userDao.createUser(user);
    }

    @RequestMapping(value = "fruits")
    public String[] getFruits() {
        final String[] fruits = { "Apple", "Banana", "Tangerine" };
        return fruits;
    }
}
