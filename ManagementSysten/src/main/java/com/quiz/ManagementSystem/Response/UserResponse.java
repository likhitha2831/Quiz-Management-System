package com.quiz.ManagementSystem.Response;

import com.quiz.ManagementSystem.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserResponse {
    private final String email;
    private final String role;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.role = user.getRole().toString();
    }
}
