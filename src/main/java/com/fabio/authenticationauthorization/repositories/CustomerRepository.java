package com.fabio.authenticationauthorization.repositories;

import com.fabio.authenticationauthorization.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
