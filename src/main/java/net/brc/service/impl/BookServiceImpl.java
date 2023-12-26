package net.brc.service.impl;

import net.brc.model.Book;
import net.brc.repo.BookRepo;
import net.brc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void update(Book book) {
        bookRepo.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepo.delete(book);
    }
}
