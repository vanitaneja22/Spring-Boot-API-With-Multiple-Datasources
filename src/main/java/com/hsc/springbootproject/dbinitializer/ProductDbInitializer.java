package com.hsc.springbootproject.dbinitializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.hsc.springbootproject.product.entity.Product;
import com.hsc.springbootproject.product.repository.ProductRepository;

@Component
@ConditionalOnProperty(name = "app.init-db", havingValue = "true")
public class ProductDbInitializer implements CommandLineRunner{

	private ProductRepository repository;
	
	public ProductDbInitializer(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.deleteAll();

        Product product1 = new Product(200, "iWatch", 10, 25000);
        Product product2 = new Product(201, "Cable", 30, 1000);

        this.repository.save(product1);
        this.repository.save(product2);

        System.out.println(" -- Database has been initialized");
    }

}
