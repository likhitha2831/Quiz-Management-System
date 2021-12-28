package com.quiz.ManagementSystem.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User is not found for given email address");
    }
}
