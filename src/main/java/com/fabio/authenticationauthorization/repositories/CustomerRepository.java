package com.fabio.authenticationauthorization.repositories;

import com.fabio.authenticationauthorization.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Transactional(readOnly = true)
    Customer findByEmail(String email);
}
