package com.quiz.ManagementSystem.BankAccount;

import com.quiz.ManagementSystem.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Getter
    private Long amount;
    @Id
    @Getter
    private String accountNumber;
    @Getter
    private String branchName;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public static Account create(CreateAccountCommand accountCommand, User user) {
        return new Account(accountCommand.getAmount(), UUID.randomUUID().toString(), accountCommand.getBranchName(), user);
    }

    public void creditToAccount(Long creditAmount) {
        this.amount += creditAmount;
    }

    public void debitFromAccount(Long debitAmount) {
        this.amount -= debitAmount;
    }

}
