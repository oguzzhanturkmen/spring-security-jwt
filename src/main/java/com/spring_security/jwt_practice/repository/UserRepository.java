package com.spring_security.jwt_practice.repository;

import com.spring_security.jwt_practice.domain.enums.User;
import com.spring_security.jwt_practice.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username) throws ResourceNotFoundException;
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
