package com.hsc.springbootproject.book.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hsc.springbootproject.book.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

	Book findByTitle(String title);
}
