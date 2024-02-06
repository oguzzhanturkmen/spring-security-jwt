package com.spring_security.jwt_practice.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
