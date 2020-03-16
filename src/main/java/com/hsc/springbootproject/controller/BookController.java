package com.hsc.springbootproject.controller;
import java.util.List;

import com.hsc.springbootproject.book.model.Book;
import com.hsc.springbootproject.book.service.BookService;


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
    private BookService service;

    @PostMapping("/")
    public Book addBook(@RequestBody Book book) {
        return service.saveBook(book);
    }

    @GetMapping("/")
    public List<Book> findAllBooks(){
        return service.getBooks();
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable int id) {
        return service.getBookById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        return service.deleteBook(id);
    }
    
    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return service.updateBook(book);
    }
}