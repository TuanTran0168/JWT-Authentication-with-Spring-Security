package com.tuantran.jwt_authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuantran.jwt_authentication.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);
}
