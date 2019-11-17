package com.example.hexa.application.service;

import com.example.hexa.application.port.in.RegisterBookUseCase;
import com.example.hexa.application.port.out.PersistBookPort;
import com.example.hexa.domain.Author;
import com.example.hexa.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class RegisterBookServiceTest {

    @Mock
    private PersistBookPort persistBookPort;

    @InjectMocks
    private RegisterBookService registerBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(persistBookPort);
    }

    @Test
    void registerBook() {
        registerBookService.registerBook(new RegisterBookUseCase.RegisterBookCommand("My Title", "John Doe"));
        verify(persistBookPort, times(1)).saveBook(new Book("My Title", new Author("John Doe")));
    }

}
