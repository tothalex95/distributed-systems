package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.example.validator.UserValidator;

import java.util.Collection;
import java.util.Collections;

public class UserControllerImpl implements UserController {

    private UserService userService;
    private Collection<UserValidator> userValidators;

    public UserControllerImpl(UserService userService, Collection<UserValidator> userValidators) {
        this.userService = userService;
        this.userValidators = userValidators;
    }

    private boolean isValid(User user) {
        return userValidators.stream().noneMatch(u -> !u.isValid(user));
    }

    @Override
    public void save(User user) {
        if (isValid(user)) {
            userService.save(user);
        }
        else {
            System.out.println("invalid");
        }
    }

}
