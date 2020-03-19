package com.hsc.springbootproject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hsc.springbootproject.book.model.Book;
import com.hsc.springbootproject.book.repository.BookRepository;
import com.hsc.springbootproject.book.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootProjectApplicationBookTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private BookService service;
	
	@MockBean
	private BookRepository repository;
	
	@Test
	public void getBooksTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Book(101, "Thinking In Java", "Bruce Eckel"), 
						new Book(105, "Core Java Volume - I Fundamentals", "Horstmann")).collect(Collectors.toList()));
		assertEquals(2, service.getBooks().size());
	}
	
	@Test
	public void saveBookTest() {
		Book book = new Book(100, "Java Concurrency In Practice-II", "Brian Goetz");
		when(repository.save(book)).thenReturn(book);
		assertEquals(book, service.saveBook(book));
	}
	
	@Test
	public void updateBookTest() {
		Book book = new Book();
		book.setId(102);
		book.setTitle("Java Concurrency In Practice-II");
		book.setAuthor("Brian Goetz");
		when(repository.save(book)).thenReturn(book);
		assertEquals(book, service.updateBook(book));
	}

	@Test
	public void deleteBookTest() {
		int bookId = 100;
		service.deleteBook(bookId);
		verify(repository, times(1)).deleteById(bookId);
	}

}
