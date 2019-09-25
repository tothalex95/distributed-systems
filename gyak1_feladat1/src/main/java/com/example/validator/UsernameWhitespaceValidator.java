package com.example.validator;

import com.example.model.User;

public class UsernameWhitespaceValidator implements UserValidator {

    @Override
    public boolean isValid(User user) {
        if (user == null || user.getUsername() == null) {
            return false;
        }
        return user.getUsername().chars().noneMatch(c -> (Character.valueOf((char) c).equals(' ')));
    }

}
