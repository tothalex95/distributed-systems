package com.example.hexa.adapter;

import com.example.hexa.adapter.persist.AuthorEntity;
import com.example.hexa.adapter.persist.AuthorRepository;
import com.example.hexa.adapter.persist.BookEntity;
import com.example.hexa.adapter.persist.BookRepository;
import com.example.hexa.domain.Author;
import com.example.hexa.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookPersistAdapterTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookPersistAdapter bookPersistAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(authorRepository, bookRepository);
    }

    @Test
    void saveBook() {
        when(authorRepository.findAuthorEntityByName("John Doe")).thenReturn(new AuthorEntity(1L, "John Doe"));
        bookPersistAdapter.saveBook(new Book("My Title", new Author("John Doe")));
        verify(authorRepository, times(1)).findAuthorEntityByName("John Doe");
        verify(bookRepository, times(1)).save(new BookEntity(null, "My Title", new AuthorEntity(1L, "John Doe")));

        when(authorRepository.findAuthorEntityByName("James Bond")).thenReturn(null);
        assertThrows(AuthorNotFoundException.class, () -> bookPersistAdapter.saveBook(new Book("My Title", new Author("James Bond"))));
        verify(authorRepository, times(1)).findAuthorEntityByName("James Bond");
    }
}
