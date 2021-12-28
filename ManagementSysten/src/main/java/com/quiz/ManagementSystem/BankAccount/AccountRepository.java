package com.quiz.ManagementSystem.BankAccount;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
