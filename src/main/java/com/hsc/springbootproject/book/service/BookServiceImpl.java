package com.hsc.springbootproject.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsc.springbootproject.book.model.Book;
import com.hsc.springbootproject.book.repository.BookRepository;
import com.hsc.springbootproject.exceptions.BookNotFoundException;
import com.hsc.springbootproject.exceptions.ProductNotFoundException;
import com.hsc.springbootproject.product.entity.Product;

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
		return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}
	
	@Override
	public Book getBookByTitle(@PathVariable String title) {
        return repository.findByTitle(title);
    }
	
	
	@Override
	public String deleteBook(int id) {
		repository.deleteById(id);
		return "{ \"message\": \"Book removed! " +id +"\"}";
	}
	
	@Override
	public Book updateBook(@RequestBody Book book) {
		int bookId = book.getId();
		Book b = repository.findById(bookId).orElseThrow
				(()->new BookNotFoundException(bookId));
        b.setId(book.getId());
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        return repository.save(b);
        }
}