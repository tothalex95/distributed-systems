package com.example.hexa.adapter;

import com.example.hexa.adapter.persist.AuthorEntity;
import com.example.hexa.adapter.persist.AuthorRepository;
import com.example.hexa.application.port.out.PersistAuthorPort;
import com.example.hexa.domain.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorPersistAdapter implements PersistAuthorPort {

    private final AuthorRepository authorRepository;

    @Override
    public void saveAuthor(Author author) {
        AuthorEntity authorEntity = authorRepository.save(new AuthorEntity(null, author.getName()));
        log.debug("entity saved {}", authorEntity);
    }

}
