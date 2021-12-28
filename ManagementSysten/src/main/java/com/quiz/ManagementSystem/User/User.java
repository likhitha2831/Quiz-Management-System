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
    public String address;

    @NotBlank(message = "pan number is mandatory")
    @Getter
    private String panNumber;

    @NotBlank(message = "Adhaar card number is mandatory")
    @Getter
    private String adhaarCard;

    @NotBlank(message = "phone number is mandatory")
    @Getter
    private String phoneNumber;

    private User(String email, String password, Role role, String address, String panNumber, String adhaarCard, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.adhaarCard = adhaarCard;
        this.panNumber = panNumber;
        this.phoneNumber = phoneNumber;
    }

    public static User create(CreateUserCommand userCommand, Role role) {
        String password = "";
        if (!userCommand.getPassword().isEmpty()) {
            password = encryptPassword(userCommand.getPassword());
        }
        return new User(userCommand.getEmail(), password, role, userCommand.getAddress(), userCommand.getPanNumber(),
                userCommand.getAdhaarCard(), userCommand.getPhoneNumber());
    }

    public static String encryptPassword(String password) {
        return PASSWORD_ENCODER.encode(password);
    }

    public static boolean isPasswordValid(String dbPassword, String password) {
        return PASSWORD_ENCODER.matches(password, dbPassword);
    }
}
