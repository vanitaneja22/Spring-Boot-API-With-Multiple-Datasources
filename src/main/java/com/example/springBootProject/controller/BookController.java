package com.example.springBootProject.controller;
import java.util.List;
import java.util.Optional;

import com.example.springBootProject.book.model.Book;
import com.example.springBootProject.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookRepository repository;

    @PostMapping("/")
    public Book saveBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @GetMapping("/")
    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable int id) {
        return repository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        repository.deleteById(id);
        return "{ \"message\": \"Book removed! " +id +"\"}";
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        Book b = repository.findById(book.getId()).orElse(null);
        assert b != null;
        b.setId(book.getId());
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        return repository.save(b);
    }
}