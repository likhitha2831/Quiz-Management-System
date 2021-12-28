package com.quiz.ManagementSystem.exception;

public class UnAuthorizedUserException extends Exception{
    public UnAuthorizedUserException() {
        super("Username or password is invalid");
    }
}
