package com.quiz.ManagementSystem.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginDetails {
    private String email;
    private String password;
}
