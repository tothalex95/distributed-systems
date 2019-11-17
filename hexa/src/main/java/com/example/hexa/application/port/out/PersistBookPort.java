package com.example.hexa.application.port.out;

import com.example.hexa.domain.Book;

public interface PersistBookPort {

    void saveBook(Book book);

}
