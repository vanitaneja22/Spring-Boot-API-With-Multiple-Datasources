package com.example.springBootProject.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootProject.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{   //first agr is model anme and second is datatype of id i.e. int

	Product findByName(String name);
}
