package com.example.springBootProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootProject.book.model.Book;
import com.example.springBootProject.book.repository.BookRepository;
import com.example.springBootProject.product.entity.Product;
import com.example.springBootProject.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProjectController {

	@Autowired
	@Qualifier("mariaDbJdbcTemplate")
	private JdbcTemplate mariaDbTemplate;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ProductService service;
	
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}
	
	@PostMapping("/product/batch")
	public List<Product> addProducts(@RequestBody List<Product> products){
		return service.saveProducts(products);
	}
	
	//if there's any input that has to be passed as a request url then use @PathVariable
	@GetMapping("/product")
	public List<Product> findAllProducts() {
		return service.getProducts();
	}
	
	@GetMapping("/product/{id}")
	public Product findProductById(@PathVariable int id) {
		return service.getProductById(id);
	}
	
	@GetMapping("/product/name/{name}")
	public Product findProductByName(@PathVariable String name) {
		return service.getProductByName(name);
	}
	
	@PutMapping("/product/update")
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}
	
	@DeleteMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
	
	@Autowired
	private BookRepository repository;
	
	@PostMapping("/book")
	public Book saveBook(@RequestBody Book book) {
		return repository.save(book);
	}
	
	@GetMapping("/book")
	public List<Book> getAllBooks(){
		return repository.findAll();
	}
	
	@GetMapping("/book/{id}")
	public Optional<Book> getBookById(@PathVariable int id) { 
		return repository.findById(id);
	}
	
	@DeleteMapping("/book/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		repository.deleteById(id);
		return "{ \"message\": \"Book removed! " +id +"\"}";
	}
	
	@PutMapping("/book/update")
	public Book updateBook(@RequestBody Book book) {
		Book b = repository.findById(book.getId()).orElse(null);
		assert b != null;
		b.setId(book.getId());
		b.setTitle(book.getTitle());
		b.setAuthor(book.getAuthor());
		return repository.save(b);
	}

}
