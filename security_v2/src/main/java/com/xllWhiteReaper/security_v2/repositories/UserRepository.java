package com.xllWhiteReaper.security_v2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xllWhiteReaper.security_v2.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
