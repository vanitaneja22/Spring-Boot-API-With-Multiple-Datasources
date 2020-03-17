package com.hsc.springbootproject.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super("Book not found for id: "+id);
    }
}
