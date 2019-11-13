package com.example.hexa.application.service;

import com.example.hexa.application.port.in.RegisterAuthorUseCase;
import com.example.hexa.application.port.out.PersistAuthorPort;
import com.example.hexa.common.UseCase;
import com.example.hexa.domain.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class RegisterAuthorService implements RegisterAuthorUseCase {

    private final PersistAuthorPort persistAuthorPort;

    @Override
    public void registerAuthor(RegisterAuthorCommand command) {
        log.debug("incoming registerAuthor {}", command);
        persistAuthorPort.saveAuthor(new Author(command.getName()));
    }

}
