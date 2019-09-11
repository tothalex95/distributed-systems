package com.example.validator;

import com.example.model.User;

public class UserValidatorImpl implements UserValidator {

    @Override
    public boolean isValid(User user) {
        if (user == null) {
            return false;
        }
        if (user.getUsername() == null) {
            return false;
        }
        if (user.getUsername().length() < 6) {
            return false;
        }
        if (user.getUsername().contains(" ")) {
            return false;
        }
        return true;
    }

}
