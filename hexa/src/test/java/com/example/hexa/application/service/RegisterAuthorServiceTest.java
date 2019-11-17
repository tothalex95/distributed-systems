package com.example.hexa.application.service;

import com.example.hexa.application.port.in.RegisterAuthorUseCase;
import com.example.hexa.application.port.out.PersistAuthorPort;
import com.example.hexa.domain.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;

class RegisterAuthorServiceTest {

    @Mock
    private PersistAuthorPort persistAuthorPort;

    @InjectMocks
    private RegisterAuthorService registerAuthorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(persistAuthorPort);
    }

    @Test
    void registerAuthor() {
        registerAuthorService.registerAuthor(new RegisterAuthorUseCase.RegisterAuthorCommand("John Doe"));
        verify(persistAuthorPort, times(1)).saveAuthor(new Author("John Doe"));
    }
}
