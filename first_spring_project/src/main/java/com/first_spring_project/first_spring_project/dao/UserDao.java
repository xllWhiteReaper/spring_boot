package com.first_spring_project.first_spring_project.dao;

import java.util.List;

import com.first_spring_project.first_spring_project.models.User;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(Long id);

    String test();

    void deleteUserById(Long id);
}