package com.hsc.springbootproject.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product not found for id: "+id);
    }
}