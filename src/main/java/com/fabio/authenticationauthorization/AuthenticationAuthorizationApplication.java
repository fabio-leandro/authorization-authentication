package com.fabio.authenticationauthorization;

import com.fabio.authenticationauthorization.domain.Customer;
import com.fabio.authenticationauthorization.domain.Product;
import com.fabio.authenticationauthorization.domain.PurchaseOrder;
import com.fabio.authenticationauthorization.domain.enuns.Perfil;
import com.fabio.authenticationauthorization.repositories.CustomerRepository;
import com.fabio.authenticationauthorization.repositories.ProductRepository;
import com.fabio.authenticationauthorization.repositories.PurchaseOrderRepository;
import com.fabio.authenticationauthorization.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationAuthorizationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Customer c1 = new Customer(null, "Fabio", "fabio@gmail.com", passwordEncoder.encode("123"));
		Customer c2 = new Customer(null, "Maria", "maria@gmail.com", passwordEncoder.encode("456"));
		Customer c3 = new Customer(null,"Priscila", "priscila@gmail.com", passwordEncoder.encode("789"));
		Customer c4 = new Customer(null, "Sarah", "sarah@hotmail.com",passwordEncoder.encode("555"));

		c4.addPerfil(Perfil.ADMIN);

		Product p1 = new Product(null, "bola",80.00);
		Product p2 = new Product(null, "chuteira", 250.00);
		Product p3 = new Product(null, "calcao", 45.00);

		customerRepository.saveAll(List.of(c1,c2,c3,c4));
		productRepository.saveAll(List.of(p1,p2,p3));

		PurchaseOrder o1 = new PurchaseOrder(null, sdf.parse("23/05/2022"),c1);
		o1.getProducts().add(p1);
		o1.getProducts().add(p2);
		purchaseOrderRepository.save(o1);

		PurchaseOrder o2 = new PurchaseOrder(null, sdf.parse("10/06/2022"),c1);
		o2.getProducts().add(p3);
		o2.getProducts().add(p2);
		purchaseOrderRepository.save(o2);

		PurchaseOrder o3 = new PurchaseOrder(null, sdf.parse("10/06/2022"),c2);
		o3.getProducts().add(p1);
		o3.getProducts().add(p2);
		purchaseOrderRepository.save(o3);

		PurchaseOrder o4 = new PurchaseOrder(null, sdf.parse("10/06/2022"),c2);
		o4.getProducts().add(p1);
		o4.getProducts().add(p2);
		purchaseOrderRepository.save(o4);

		emailService.send("fabio@gmail.com","Teste Spring","Estamos fazendo o teste.");

	}
}
