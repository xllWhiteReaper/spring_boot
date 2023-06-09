package com.first_spring_project.first_spring_project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import com.first_spring_project.first_spring_project.dao.UserDao;
import com.first_spring_project.first_spring_project.models.User;
import com.first_spring_project.first_spring_project.utils.JWTUtil;

@RestController()
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> getAllUsers(@RequestHeader(value = "Authorization") String jwtToken) {
        return validateToken(jwtToken) ? userDao.getAllUsers() : new ArrayList<>();
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userDao.getUserById(id);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable Long id, @RequestHeader(value = "Authorization") String jwtToken) {
        if (validateToken(jwtToken)) {
            userDao.deleteUserById(id);
        }
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userDao.createUser(user);
    }

    private boolean validateToken(String jwtToken) {
        try {
            String userId = jwtUtil.getKey(jwtToken);
            return userId != null ? true : false;
        } catch (Exception e) {
            return false;
        }
    }
}
