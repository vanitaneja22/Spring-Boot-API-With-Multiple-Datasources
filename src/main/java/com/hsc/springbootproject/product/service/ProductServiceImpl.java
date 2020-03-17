package com.hsc.springbootproject.product.service;

import java.util.List;

import com.hsc.springbootproject.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsc.springbootproject.product.entity.Product;
import com.hsc.springbootproject.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	//save single product to database
	@Override
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	//save the entire object to database
	@Override
	public List<Product> saveProducts(List<Product> products) {
		return repository.saveAll(products);
	}
	
	@Override
	public Product getProductById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	public List<Product> getProducts(){
		return repository.findAll();
	}
	
	@Override
	public Product getProductByName(String name){
		return repository.findByName(name);
	}
	
	@Override
	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "{ \"message\": \"Product removed! " +id +"\"}";
	}
	
	@Override
	public Product updateProduct(Product product) {
		int productId = product.getId();
		Product p = repository.findById(productId).orElseThrow
				(()->new ProductNotFoundException(productId));
		p.setName(product.getName());
		p.setQuantity(product.getQuantity());
		p.setPrice(product.getPrice());
		return repository.save(p);
	}

}
