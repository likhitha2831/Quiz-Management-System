package com.quiz.ManagementSystem.Response;

import com.quiz.ManagementSystem.BankAccount.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AccountResponse {
    private String accountNumber;
    private Long balanceAmount;

    public AccountResponse(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.balanceAmount = account.getAmount();
    }
}
