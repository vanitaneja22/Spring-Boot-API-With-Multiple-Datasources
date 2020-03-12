package com.example.springBootProject.book.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.springBootProject.book.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
