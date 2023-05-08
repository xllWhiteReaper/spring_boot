package com.first_spring_project.first_spring_project.repositories;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.first_spring_project.first_spring_project.models.User;

@Repository
public class UserRepository {
    private User[] usersList = {
            new User(
                    "cobra",
                    "github",
                    "fake1@gmail.com",
                    "123456789",
                    "definitelyNotMyDefaultPass2022",
                    1L),
            new User(
                    "xllWhiteReaper",
                    "github",
                    "fake2@gmail.com",
                    "987654321",
                    "definitelyNotMyDefaultPass",
                    2L)
    };

    public User[] getAllUsers() {
        return usersList;
    }

    public User getUserById(String id) {
        for (User user : usersList) {
            if ((user.getId() + "").equals(id)) {
                return user;
            }
        }
        return null;
        // return new ClassPathResource("static/404.html");
    }
}
