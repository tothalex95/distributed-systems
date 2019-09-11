package com.example.validator;

import com.example.model.User;

public class UsernameLengthValidator implements UserValidator {

    @Override
    public boolean isValid(User user) {
        if (user == null || user.getUsername() == null) {
            return false;
        }
        return user.getUsername().length() >= 6;
    }

}
