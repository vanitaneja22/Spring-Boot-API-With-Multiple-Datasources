package com.example.springBootProject.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mongodb.MongoClientURI;

@Configuration
public class MultipleDBConfig {
	
	@Bean(name="mariaDb")
	@ConfigurationProperties(prefix="spring.product.datasource")
	public DataSource mariaDbDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="mariaDbJdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("mariaDb") DataSource dsMariaDb) {
		return new JdbcTemplate(dsMariaDb);
	}
	
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
}
