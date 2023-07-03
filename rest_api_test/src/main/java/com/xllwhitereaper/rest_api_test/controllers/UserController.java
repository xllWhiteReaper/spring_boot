package com.xllwhitereaper.rest_api_test.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xllwhitereaper.rest_api_test.models.User;
import com.xllwhitereaper.rest_api_test.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            return ResponseEntity.ok(foundUser);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) {
        if (user.getId() == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            userRepository.delete(foundUser);
            return ResponseEntity.ok(foundUser);
        }
        return ResponseEntity.notFound().build();
    }
}
