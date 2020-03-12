package com.example.springBootProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

@Configuration
public class BookDBConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("spring.data.mongodb.url")));
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		 MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		 return mongoTemplate;
	}
	
//	@Bean
//	public MongoClientFactoryBean mongoClientFactoryBean() {
//		 MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
//		 factoryBean.setHost("localhost");
//		 return factoryBean;
//	}
}





