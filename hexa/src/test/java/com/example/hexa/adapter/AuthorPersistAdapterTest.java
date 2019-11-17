package com.example.hexa.adapter;

import com.example.hexa.adapter.persist.AuthorEntity;
import com.example.hexa.adapter.persist.AuthorRepository;
import com.example.hexa.domain.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorPersistAdapterTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorPersistAdapter authorPersistAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(authorRepository);
    }

    @Test
    void saveAuthor() {
        authorPersistAdapter.saveAuthor(new Author("John Doe"));
        verify(authorRepository, times(1)).save(new AuthorEntity(null, "John Doe"));
    }
}
