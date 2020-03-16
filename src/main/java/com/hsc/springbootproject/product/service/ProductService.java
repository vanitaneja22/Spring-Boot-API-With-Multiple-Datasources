package com.hsc.springbootproject.product.service;

import java.util.List;

import com.hsc.springbootproject.product.entity.Product;

public interface ProductService {
	public abstract Product saveProduct(Product product);
	public abstract List<Product> saveProducts(List<Product> products);
	public abstract List<Product> getProducts();
	public abstract Product getProductById(int id);
	public abstract Product getProductByName(String name);
	public abstract String deleteProduct(int id);
	public abstract Product updateProduct(Product product);
}
