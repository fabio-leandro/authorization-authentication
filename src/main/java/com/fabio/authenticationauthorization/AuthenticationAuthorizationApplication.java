package com.fabio.authenticationauthorization;

import com.fabio.authenticationauthorization.domain.Customer;
import com.fabio.authenticationauthorization.domain.Product;
import com.fabio.authenticationauthorization.domain.PurchaseOrder;
import com.fabio.authenticationauthorization.repositories.CustomerRepository;
import com.fabio.authenticationauthorization.repositories.ProductRepository;
import com.fabio.authenticationauthorization.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
public class AuthenticationAuthorizationApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationAuthorizationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Customer c1 = new Customer(null, "Fabio", "fabio@gmail.com");
		Customer c2 = new Customer(null, "Maria", "maria@gmail.com");
		Customer c3 = new Customer(null,"Priscila", "priscila@gmail.com");

		Product p1 = new Product(null, "bola",80.00);
		Product p2 = new Product(null, "chuteira", 250.00);
		Product p3 = new Product(null, "calcao", 45.00);

		customerRepository.saveAll(List.of(c1,c2,c3));
		productRepository.saveAll(List.of(p1,p2,p3));

		PurchaseOrder o1 = new PurchaseOrder(null, sdf.parse("23/05/2022"),c1);
		o1.getProducts().add(p1);
		o1.getProducts().add(p2);
		purchaseOrderRepository.save(o1);

//		PurchaseOrder o2 = new PurchaseOrder(null,sdf.parse("22/05/2022"),c1);
////		o2.getProducts().add(p1);
////		o2.getProducts().add(p2);
//		o2.getProducts().add(p3);
//		purchaseOrderRepository.save(o2);


	}
}
