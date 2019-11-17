package com.example.hexa.adapter.integration;

import com.example.hexa.application.port.in.RegisterBookUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterBookActivatorTest {

    @Mock
    private RegisterBookUseCase registerBookUseCase;

    @InjectMocks
    private RegisterBookActivator registerBookActivator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(registerBookUseCase);
    }

    @Test
    void registerBook() {
        Message<RegisterBookUseCase.RegisterBookCommand> message = mock(Message.class);
        when(message.getPayload()).thenReturn(new RegisterBookUseCase.RegisterBookCommand("My Title", "John Doe"));
        registerBookActivator.registerBook(message);
        verify(registerBookUseCase, times(1)).registerBook(new RegisterBookUseCase.RegisterBookCommand("My Title", "John Doe"));
    }
}
