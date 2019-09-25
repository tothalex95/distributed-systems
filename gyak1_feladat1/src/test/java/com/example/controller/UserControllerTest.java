package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.example.validator.UserValidator;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

class UserControllerTest {

    @Test
    public void save_notValidUser_saveNotCalled() {
        UserValidator validator = Mockito.mock(UserValidator.class);
        Mockito.when(validator.isValid(Mockito.any())).thenReturn(false);
        UserService service = Mockito.mock(UserService.class);

        UserController userController = new UserControllerImpl(service, Arrays.asList(validator));

        userController.save(new User("assadas"));

        Mockito.verify(service, Mockito.never()).save(Mockito.any(User.class));
    }

    @Test
    public void save_validUser_saveCalledOnce() {
        UserValidator validator = Mockito.mock(UserValidator.class);
        Mockito.when(validator.isValid(Mockito.any())).thenReturn(true);
        UserService service = Mockito.mock(UserService.class);

        UserController userController = new UserControllerImpl(service, Arrays.asList(validator));

        userController.save(new User("assadas"));

        Mockito.verify(service, Mockito.times(1)).save(Mockito.any(User.class));
    }

}