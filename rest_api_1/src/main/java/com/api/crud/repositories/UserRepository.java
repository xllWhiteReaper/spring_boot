package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.crud.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
