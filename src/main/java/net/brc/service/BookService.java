package net.brc.service;

import net.brc.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> findAll();
    Book save(Book book);

    void update(Book book);
    void delete(Book book);
}
