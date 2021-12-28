package com.quiz.ManagementSystem.User;

import com.quiz.ManagementSystem.Response.UserResponse;
import com.quiz.ManagementSystem.exception.InvalidEmailException;
import com.quiz.ManagementSystem.exception.UnAuthorizedUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value="/createUser", consumes = "application/json")
    ResponseEntity<UserResponse> create(@RequestBody @Valid CreateUserCommand userCommand) throws InvalidEmailException {
        User user = userService.create(userCommand, userCommand.getRole());
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }

    @PostMapping(value="/login", consumes = "application/json")
    void login(@RequestBody @Valid UserLoginDetails userLoginDetails) throws UnAuthorizedUserException {
        userService.validateUserDetails(userLoginDetails);
    }

    @PutMapping(value="/userDetails", consumes = "application/json")
    void updateUserDetails(@RequestBody @Valid CreateUserCommand userCommand) {
        userService.updateUserDetails(userCommand);
    }

    @PostMapping(value = "/admin", consumes = "application/json")
    ResponseEntity<UserResponse> createAdminUsers(@RequestBody @Valid CreateUserCommand adminCommand)
            throws InvalidEmailException {
        User user = userService.create(adminCommand, adminCommand.getRole());
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }
}
