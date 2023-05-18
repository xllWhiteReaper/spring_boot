package com.first_spring_project.first_spring_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.first_spring_project.first_spring_project.dao.UserDao;
import com.first_spring_project.first_spring_project.models.User;

@RestController()
@RequestMapping("/api")
public class AuthController {
	@Autowired
	UserDao userDao;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestBody User user) {
		return userDao.verifyCredentials(user) ? "OK" : "INCORRECT";
	}
}
