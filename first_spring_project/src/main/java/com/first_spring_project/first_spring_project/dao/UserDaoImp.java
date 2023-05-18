package com.first_spring_project.first_spring_project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.first_spring_project.first_spring_project.models.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        String query = "From User";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        return typedQuery.getResultList();
    }

    @Override
    public String test() {
        String query = "FROM User";
        return query;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        User foundUser = entityManager.find(User.class, id);
        return foundUser;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public boolean verifyCredentials(User user) {
        String query = "FROM User WHERE email = :email AND password = :password";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        List<User> userList = typedQuery.setParameter("email", user.getEmail())
                .setParameter("password", user.getPassword()).getResultList();
                
        return !userList.isEmpty();
    }
}
