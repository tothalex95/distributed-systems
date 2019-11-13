package com.example.hexa.application.port.out;

import com.example.hexa.domain.Author;

public interface PersistAuthorPort {

    void saveAuthor(Author author);

}
