package com.quiz.ManagementSystem.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class CreateUserCommand {
    @Email(message = "Email should be in format 'username@abc.com'")
    @NotBlank(message = "Email is mandatory")
    private final String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$",
            message = "Password must be 8 or more characters in length, must contain 1 or more lowercase characters, "
                    + "must contain 1 or more uppercase characters and must contain 1 or more special characters.")
    @NotBlank(message = "Password is mandatory")
    private final String password;

    @Valid
    @NotBlank(message = "Address is mandatory")
    private final String address;

    @NotBlank(message = "pan number is mandatory")
    private final String panNumber;

    @NotBlank(message = "Adhaar card number is mandatory")
    private final String adhaarCard;

    @NotBlank(message = "phone number is mandatory")
    private final String phoneNumber;

    @Enumerated(EnumType.STRING)
    private final Role role;
}
