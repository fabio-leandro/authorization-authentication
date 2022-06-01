package com.fabio.authenticationauthorization.services;

import com.fabio.authenticationauthorization.domain.Customer;
import com.fabio.authenticationauthorization.exceptions.ObjectNotDeletedException;
import com.fabio.authenticationauthorization.exceptions.ObjectNotFoundException;
import com.fabio.authenticationauthorization.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Customer not found with id -> "+id));
    }

    public Customer update(Long id, Customer customer){
        getById(id);
        return customerRepository.save(customer);
    }

    public void delete(Long id){
        getById(id);
        try {
            customerRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ObjectNotDeletedException("The customer cannot be deleted." +
                    " It might was used in another object.");
        }

    }
}
