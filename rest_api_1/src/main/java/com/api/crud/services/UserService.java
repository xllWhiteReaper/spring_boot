package com.api.crud.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.User;
import com.api.crud.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUserById(User userToUpdate) {
        User user = userRepository.findById(userToUpdate.getId()).get();
        if (user != null) {
            user.setFirstName(userToUpdate.getFirstName());
            user.setLastName(userToUpdate.getLastName());
            user.setEmail(userToUpdate.getEmail());
            userRepository.save(user);
        }
        return user;
    }

    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
