package com.fabio.authenticationauthorization.repositories;

import com.fabio.authenticationauthorization.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
