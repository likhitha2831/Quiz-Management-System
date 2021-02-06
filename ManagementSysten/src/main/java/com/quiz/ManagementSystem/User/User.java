package com.quiz.ManagementSystem.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @NotBlank(message = "Email is mandatory")
    @Getter
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Getter
    private String password;
    @Enumerated(EnumType.STRING)
    @Getter
    private Role role;
    @Getter
    private int score;

    private User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User create(CreateUserCommand userCommand, Role role) {
        String password = "";
        if (!userCommand.getPassword().isEmpty()) {
            password = PASSWORD_ENCODER.encode(userCommand.getPassword());
        }
        return new User(userCommand.getEmail(), password, role);
    }
}
