package com.example.springBootProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootProject.book.repository.BookRepository;
import com.example.springBootProject.product.entity.Product;
import com.example.springBootProject.product.repository.ProductRepository;
import com.example.springBootProject.product.service.ProductService;



@SpringBootApplication
@RestController
@ComponentScan(basePackages="{com.example.springBootProject.*}")
public class SpringBootProjectApplication {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ProductService service;
	
	@PostMapping("/")
	public Product addProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}
	
	@PostMapping("/batch")
	public List<Product> addProducts(@RequestBody List<Product> products){
		return service.saveProducts(products);
	}
	
	//if there's any input that has to be passed as a request url then use @PathVariable
	@GetMapping("/")
	public List<Product> findAllProducts() {
		return service.getProducts();
	}
	
	@GetMapping("/{id}")
	public Product findProductById(@PathVariable int id) {
		return service.getProductById(id);
	}
	
	@GetMapping("/name/{name}")
	public Product findProductByName(@PathVariable String name) {
		return service.getProductByName(name);
	}
	
	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}

}
