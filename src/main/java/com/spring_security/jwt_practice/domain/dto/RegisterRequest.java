package com.spring_security.jwt_practice.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegisterRequest {




    @NotBlank
    @NotNull
    @Size(min = 1, max = 15, message = "First name '${validatedValue}' must be between {min} and {max} characters")
    private String firstName;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 15, message = "Last name '${validatedValue}' must be between {min} and {max} characters")
    private String lastName;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20, message = "Username '${validatedValue}' must be between {min} and {max} characters")
    private String username;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20, message = "Password '${validatedValue}' must be between {min} and {max} characters")
    private String password;

    private Set<String> roles;


}