package com.example.springBootProject.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBootProject.product.entity.Product;
import com.example.springBootProject.product.repository.ProductRepository;


@Service
public class ProductService {

	//ProductService will talk to ProductRepository thus need to be injected
	@Autowired
	private ProductRepository repository;
	
	//save single product to database
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	//save the entire object to database
	public List<Product> saveProducts(List<Product> products) {
		return repository.saveAll(products);
	}
	
	public Product getProductById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Product> getProducts(){
		return repository.findAll();
	}
	
	public Product getProductByName(String name){
		return repository.findByName(name);
	}
	
	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "{ \"message\": \"Product removed! " +id +"\"}";
	}
	
	public Product updateProduct(Product product) {
		Product p = repository.findById(product.getId()).orElse(null);
		assert p != null;
		// always check if p is null or not then call methods on it, else nullpointerexception will arise
		p.setName(product.getName());
		p.setQuantity(product.getQuantity());
		p.setPrice(product.getPrice());
		return repository.save(p);
	}
	
}
