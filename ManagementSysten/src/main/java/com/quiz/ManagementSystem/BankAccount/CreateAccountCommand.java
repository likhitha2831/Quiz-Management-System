package com.quiz.ManagementSystem.BankAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountCommand {
    private final String email;
    private final String branchName;
    private final Long amount;
}
