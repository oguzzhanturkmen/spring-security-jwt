package com.spring_security.jwt_practice.controller;

import com.spring_security.jwt_practice.domain.dto.LoginRequest;
import com.spring_security.jwt_practice.domain.dto.RegisterRequest;
import com.spring_security.jwt_practice.security.JwtUtils;
import com.spring_security.jwt_practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJwtController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;



    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        userService.registerUser(registerRequest);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest){
       Authentication authentication = authenticationManager
               .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
       String token = jwtUtils.generateJwtToken(authentication);
       return new ResponseEntity<>(token, HttpStatus.CREATED);

    }
}
