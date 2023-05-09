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
    private EntityManager entityManager;

    @Override
    @Transactional
    public User[] getUsers() {
        String query = "FROM User";
        List<User> userList = entityManager.createQuery(query).getResultList();
        return userList.toArray(new User[userList.size()]);
    }

}
