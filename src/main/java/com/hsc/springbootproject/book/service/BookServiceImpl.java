package com.hsc.springbootproject.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsc.springbootproject.book.model.Book;
import com.hsc.springbootproject.book.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;
	
	@Override
	public Book saveBook(Book book) {
		return repository.save(book);
	}
	
	@Override
	public List<Book> getBooks(){
		return repository.findAll();
	}
	
	@Override
	public Book getBookById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	public Book getBookByName(@PathVariable String name) {
        return repository.findByName(name);
    }
	
	
	@Override
	public String deleteBook(int id) {
		repository.deleteById(id);
		return "{ \"message\": \"Book removed! " +id +"\"}";
	}
	
	@Override
	public Book updateBook(@RequestBody Book book) {
        Book b = repository.findById(book.getId()).orElse(null);
        assert b != null;
        b.setId(book.getId());
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        return repository.save(b);
        }
}