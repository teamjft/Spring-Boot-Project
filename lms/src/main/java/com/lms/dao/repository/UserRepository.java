package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.User;

/**
 * Created by bhushan on 10/4/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUuid(String uuid);
}
