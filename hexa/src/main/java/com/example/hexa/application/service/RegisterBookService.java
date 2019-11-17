package com.example.hexa.application.service;

import com.example.hexa.application.port.in.RegisterBookUseCase;
import com.example.hexa.application.port.out.PersistBookPort;
import com.example.hexa.common.UseCase;
import com.example.hexa.domain.Author;
import com.example.hexa.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class RegisterBookService implements RegisterBookUseCase {

    private final PersistBookPort persistBookPort;

    @Override
    public void registerBook(RegisterBookCommand command) {
        log.debug("incoming registerBookCommand {}", command);
        persistBookPort.saveBook(new Book(command.getTitle(), new Author(command.getAuthor())));
    }

}
