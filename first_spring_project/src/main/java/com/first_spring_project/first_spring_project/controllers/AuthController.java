package com.first_spring_project.first_spring_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.first_spring_project.first_spring_project.dao.UserDao;
import com.first_spring_project.first_spring_project.models.User;
import com.first_spring_project.first_spring_project.utils.JWTUtil;

@RestController()
@RequestMapping("/api")
public class AuthController {
	@Autowired
	UserDao userDao;

	@Autowired
	JWTUtil jwtUtil;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestBody User user) {
		User verifiedUser = userDao.getUserByCredentials(user);
		return verifiedUser != null ? jwtUtil.create(String.valueOf(verifiedUser.getId()), verifiedUser.getEmail())
				: "INCORRECT";
	}
}
