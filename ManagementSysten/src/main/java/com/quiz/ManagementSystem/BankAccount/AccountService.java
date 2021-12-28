package com.quiz.ManagementSystem.BankAccount;

import com.quiz.ManagementSystem.User.User;
import com.quiz.ManagementSystem.User.UserRepository;
import com.quiz.ManagementSystem.exception.AccountNotFountException;
import com.quiz.ManagementSystem.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account create(CreateAccountCommand accountCommand) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(accountCommand.getEmail());
        if (user.isPresent()) {
            Account account = Account.create(accountCommand, user.get());
            accountRepository.save(account);
            return account;
        } else {
            throw new UserNotFoundException();
        }

    }

    public Account creditAmountToAccount(CreditRequest creditRequest) throws AccountNotFountException {
        Optional<Account> account = accountRepository.findByAccountNumber(creditRequest.getAccountNumber());
        if (account.isPresent()) {
            Account updatedAccount = account.get();
            updatedAccount.creditToAccount(creditRequest.getCreditAmount());
            accountRepository.save(updatedAccount);
            return updatedAccount;
        } else {
            throw new AccountNotFountException("Given account number is invalid");
        }
    }

    public Account debitAmountFromAccount(CreditRequest creditRequest) throws AccountNotFountException {
        Optional<Account> account = accountRepository.findByAccountNumber(creditRequest.getAccountNumber());
        if (account.isPresent()) {
            Account updatedAccount = account.get();
            updatedAccount.debitFromAccount(creditRequest.getCreditAmount());
            accountRepository.save(updatedAccount);
            return updatedAccount;
        } else {
            throw new AccountNotFountException("Given account number is invalid");
        }
    }
}
