package com.example.hexa.adapter;

import com.example.hexa.adapter.persist.AuthorEntity;
import com.example.hexa.adapter.persist.AuthorRepository;
import com.example.hexa.adapter.persist.BookEntity;
import com.example.hexa.adapter.persist.BookRepository;
import com.example.hexa.application.port.out.PersistBookPort;
import com.example.hexa.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookPersistAdapter implements PersistBookPort {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Override
    public void saveBook(Book book) {
        log.debug("incoming book {}", book);
        AuthorEntity author = authorRepository.findAuthorEntityByName(book.getAuthor().getName());
        if (author == null) {
            throw new AuthorNotFoundException(book.getAuthor().getName());
        }
        BookEntity bookEntity = bookRepository.save(new BookEntity(null, book.getTitle(), author));
        log.debug("bookEntity saved {}", bookEntity);
    }

}
