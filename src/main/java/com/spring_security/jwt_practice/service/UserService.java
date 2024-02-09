package com.spring_security.jwt_practice.service;

import com.spring_security.jwt_practice.domain.Role;
import com.spring_security.jwt_practice.domain.User;
import com.spring_security.jwt_practice.domain.dto.RegisterRequest;
import com.spring_security.jwt_practice.domain.enums.UserRole;
import com.spring_security.jwt_practice.exception.ConflictException;
import com.spring_security.jwt_practice.repository.RoleRepository;
import com.spring_security.jwt_practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ConflictException("Username is already taken!");
        }
        Role role = roleRepository.findByName(UserRole.ROLE_STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);

    }
}
