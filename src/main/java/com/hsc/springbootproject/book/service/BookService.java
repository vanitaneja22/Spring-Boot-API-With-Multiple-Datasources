package com.hsc.springbootproject.book.service;

import java.util.List;

import com.hsc.springbootproject.book.model.Book;

public interface BookService {

	public abstract Book saveBook(Book book);
	public abstract List<Book> getBooks();
	public abstract Book getBookById(int id);
	public abstract Book getBookByTitle(String title);
	public abstract String deleteBook(int id);
	public abstract Book updateBook(Book book);
}
