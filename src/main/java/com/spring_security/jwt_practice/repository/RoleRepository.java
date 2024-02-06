package com.spring_security.jwt_practice.repository;

import com.spring_security.jwt_practice.domain.Role;
import com.spring_security.jwt_practice.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(UserRole name);
}
