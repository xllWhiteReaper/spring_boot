package com.xllwhitereaper.rest_api_test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xllwhitereaper.rest_api_test.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
