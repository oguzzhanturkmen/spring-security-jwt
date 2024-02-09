package com.spring_security.jwt_practice.repository;

import com.spring_security.jwt_practice.domain.User;
import com.spring_security.jwt_practice.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username) throws ResourceNotFoundException;
    boolean existsByUsername(String username);



}
