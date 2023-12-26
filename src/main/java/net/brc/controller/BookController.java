package net.brc.controller;

import jakarta.validation.Valid;
import net.brc.model.Book;
import net.brc.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping
    public List<Book> findAll(){
        return bookRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert-book")
    public void insert(@Valid @RequestBody Book book){
        bookRepo.save(book);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update-book/{id}")
    public void update(@Valid @RequestBody Book book, @PathVariable Long id){
        if(!bookRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found.");
        }
        bookRepo.save(book);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete-book/{id}")
    public void delete(@PathVariable Long id){
        bookRepo.deleteById(id);
    }
}
