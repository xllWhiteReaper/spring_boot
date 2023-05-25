package com.api.crud.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.models.User;
import com.api.crud.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "test")
    public String test() {
        return "this is a test";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User updateUserById(@RequestBody User user) {
        return userService.updateUserById(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable("id") Long id) {
        return userService.deleteUserById(id) ? "User with id: " + id + " was successfully deleted"
                : "There was an unexpected error, please try again";
    }
}