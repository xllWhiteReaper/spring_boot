package com.first_spring_project.first_spring_project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.first_spring_project.first_spring_project.models.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

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

    @Override
    public List<User> getAllUsers() {
        String query = "From User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public User getUserById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    @Override
    public String test() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }

    // @Override
    // @Transactional
    // public User[] getAllUsers() {
    // String query = "FROM User";
    // List<User> userList = entityManager.createQuery(query).getResultList();
    // return userList.toArray(new User[userList.size()]);
    // }

    // @Override
    // @Transactional
    // public List<User> getAllUsers() {
    //     String query = "FROM User";
    //     List<User> userList = entityManager.createQuery(query).getResultList();
    //     return userList;
    // }

    // @Transactional
    // public String test() {
    //     String query = "FROM User";
    //     return query;
    // }

    // @Transactional
    // public User getUserById(String id) {
    //     for (User user : usersList) {
    //         if ((user.getId() + "").equals(id)) {
    //             return user;
    //         }
    //     }
    //     return null;
    // }
}
