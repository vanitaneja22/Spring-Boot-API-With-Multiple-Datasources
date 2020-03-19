package com.hsc.springbootproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.hsc.springbootproject.product.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootProjectApplicationProductIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port +"/api";
	}
	
	@Test
	public void contextLoads() {

	}

	@Test
	public void getProductsTest() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/product",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void saveProductsTest() {
		Product product = new Product();
		product.setId(13);
		product.setName("Earplugs");
		product.setQuantity(30);
		product.setPrice(300);
		ResponseEntity<Product> postResponse = restTemplate.postForEntity(getRootUrl() + "/product", product, Product.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void updateProductTest() {
		int id = 9;
		Product product = restTemplate.getForObject(getRootUrl() + "/product/update" + id, Product.class);
		product.setName("Watch");
		product.setQuantity(35);
		product.setPrice(7000);
		restTemplate.put(getRootUrl() + "/product/update" + id, product);
		Product updatedProduct = restTemplate.getForObject(getRootUrl() + "product/update" + id, Product.class);
		assertNotNull(updatedProduct);
	}
	
	@Test
	public void deleteProductTest() {
		int id = 8;
		restTemplate.delete(getRootUrl() + "/product/delete" + id);
		try {
			Product product = restTemplate.getForObject(getRootUrl() + "/product/delete" + id, Product.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
