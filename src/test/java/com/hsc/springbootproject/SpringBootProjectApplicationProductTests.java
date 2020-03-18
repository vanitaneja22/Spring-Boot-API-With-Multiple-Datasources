package com.hsc.springbootproject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hsc.springbootproject.product.entity.Product;
import com.hsc.springbootproject.product.repository.ProductRepository;
import com.hsc.springbootproject.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootProjectApplicationProductTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ProductService service;
	
	@MockBean
	private ProductRepository repository;
	
	@Test
	public void getProductsTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Product(11, "Charger", 20, 500), 
						new Product(12, "Headphone", 25, 1500)).collect(Collectors.toList()));
		assertEquals(2, service.getProducts().size());
	}
	
	@Test
	public void saveProductTest() {
		Product product = new Product(10, "Airpods", 10, 10000);
		when(repository.save(product)).thenReturn(product);
		assertEquals(product, service.saveProduct(product));
	}

	@Test
	public void deleteProductTest() {
		int productId = 10;
		service.deleteProduct(productId);
		verify(repository, times(1)).deleteById(productId);
	}

}
