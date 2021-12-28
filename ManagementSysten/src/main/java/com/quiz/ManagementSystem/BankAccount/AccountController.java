package com.quiz.ManagementSystem.BankAccount;

import com.quiz.ManagementSystem.Response.AccountResponse;
import com.quiz.ManagementSystem.exception.AccountNotFountException;
import com.quiz.ManagementSystem.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/createAccount", consumes = "application/json")
    ResponseEntity<AccountResponse> create(@RequestBody @Valid CreateAccountCommand accountCommand) throws UserNotFoundException {
        Account account = accountService.create(accountCommand);
        return new ResponseEntity<>(new AccountResponse(account), HttpStatus.CREATED);
    }

    @PutMapping(value = "/credit", consumes = "application/json")
    ResponseEntity<AccountResponse> creditAmount(@RequestBody @Valid CreditRequest creditRequest) throws AccountNotFountException {
        Account account = accountService.creditAmountToAccount(creditRequest);
        return new ResponseEntity<>(new AccountResponse(account), HttpStatus.OK);
    }

    @PutMapping(value = "/debit", consumes = "application/json")
    ResponseEntity<AccountResponse> debitAmount(@RequestBody @Valid CreditRequest creditRequest) throws AccountNotFountException {
        Account account = accountService.debitAmountFromAccount(creditRequest);
        return new ResponseEntity<>(new AccountResponse(account), HttpStatus.OK);
    }
}
