package com.example.hexa.adapter.integration;

import com.example.hexa.application.port.in.RegisterAuthorUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterAuthorActivatorTest {

    @Mock
    private RegisterAuthorUseCase registerAuthorUseCase;

    @InjectMocks
    private RegisterAuthorActivator registerAuthorActivator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(registerAuthorUseCase);
    }

    @Test
    void registerAuthor() {
        Message<RegisterAuthorUseCase.RegisterAuthorCommand> message = mock(Message.class);
        when(message.getPayload()).thenReturn(new RegisterAuthorUseCase.RegisterAuthorCommand("John Doe"));
        registerAuthorActivator.registerAuthor(message);
        verify(registerAuthorUseCase, times(1)).registerAuthor(new RegisterAuthorUseCase.RegisterAuthorCommand("John Doe"));
    }
}
