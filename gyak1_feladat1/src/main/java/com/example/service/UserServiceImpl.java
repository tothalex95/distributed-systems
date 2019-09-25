package com.example.service;

import com.example.model.User;

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        System.out.println("Elmentve: " + user);
    }

}
