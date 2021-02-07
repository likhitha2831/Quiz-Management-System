package com.quiz.ManagementSystem.User;

import com.quiz.ManagementSystem.Response.UserResponse;
import com.quiz.ManagementSystem.exception.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json")
    ResponseEntity<UserResponse> create(@RequestBody @Valid CreateUserCommand userCommand) throws InvalidEmailException {
        User user = userService.create(userCommand, Role.USER);
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }

    @PostMapping(value = "/admin", consumes = "application/json")
    ResponseEntity<UserResponse> createAdminUsers(@RequestBody @Valid CreateUserCommand adminCommand)
            throws InvalidEmailException {
        User user = userService.create(adminCommand, Role.ADMIN);
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }
    //TODO api to update score for given user
    //TODO api to get score for given user
}
