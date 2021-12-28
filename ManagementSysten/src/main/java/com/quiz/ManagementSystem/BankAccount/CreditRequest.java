package com.quiz.ManagementSystem.BankAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditRequest {
    private String accountNumber;
    private Long creditAmount;
}
