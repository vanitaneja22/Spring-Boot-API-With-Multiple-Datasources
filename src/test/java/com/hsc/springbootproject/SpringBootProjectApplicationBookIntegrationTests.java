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

import com.hsc.springbootproject.book.model.Book;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootProjectApplicationBookIntegrationTests {

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
	public void getBooksTest() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/book",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void saveBooksTest() {
		Book book = new Book();
		book.setId(112);
		book.setTitle("Java Concepts");
		book.setAuthor("John Dayer");
		ResponseEntity<Book> postResponse = restTemplate.postForEntity(getRootUrl() + "/book", book, Book.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void updateBookTest() {
		int id = 102;
		Book book = restTemplate.getForObject(getRootUrl() + "/book/update" + id, Book.class);
		book.setTitle("Java Fundamentals -III");
		book.setAuthor("Tim Bergs");
		restTemplate.put(getRootUrl() + "/book/update" + id, book);
		Book updatedBook = restTemplate.getForObject(getRootUrl() + "/book/update" + id, Book.class);
		assertNotNull(updatedBook);
	}
	
	@Test
	public void deleteBookTest() {
		int id = 105;
		Book book = restTemplate.getForObject(getRootUrl() + "/book/delete" + id, Book.class);
		assertNotNull(book);

		restTemplate.delete(getRootUrl() + "/book/delete" + id);

		try {
			book = restTemplate.getForObject(getRootUrl() + "/book/delete" + id, Book.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}







