package com.xllWhiteReaper.security_v2.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xllWhiteReaper.security_v2.models.User;
import com.xllWhiteReaper.security_v2.repositories.UserRepository;

// Development only  
@Component
public class UserCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create and save the first user
        User user1 = new User();
        user1.setName("admin");
        user1.setPassword(passwordEncoder.encode("password1"));
        user1.setRole(Role.ADMINISTRATOR);
        user1.setUsername("username1");

        userRepository.save(user1);

        // Create and save the second user
        User user2 = new User();
        user2.setName("user");
        user2.setPassword(passwordEncoder.encode("password2"));
        user2.setRole(Role.CUSTOMER);
        user2.setUsername("username2");
        userRepository.save(user2);

        System.out.println("\n\n\n All Users:\n\n\n");
        List<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println("User: " + user.getName());
            System.out.println("Password: " + user.getPassword());
        }
    }
}