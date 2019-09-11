package com.example.controller;

import com.example.model.User;
import com.example.validator.UserValidator;

public class UserControllerImpl implements UserController {

    private UserValidator userValidator;

    public UserControllerImpl(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Override
    public void save(User user) {
        if (userValidator.isValid(user)) {
            System.out.println("Elmentve: " + user);
        }
        System.out.println("Sikertelen mentés: " + user);
    }

}
