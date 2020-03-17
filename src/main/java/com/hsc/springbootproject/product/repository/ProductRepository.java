package com.hsc.springbootproject.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsc.springbootproject.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{   
	Product findByName(String name);
}
