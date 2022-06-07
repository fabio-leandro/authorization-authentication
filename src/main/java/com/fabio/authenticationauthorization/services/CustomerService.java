package com.fabio.authenticationauthorization.services;

import com.fabio.authenticationauthorization.domain.Customer;
import com.fabio.authenticationauthorization.dtos.NewCustomerDto;
import com.fabio.authenticationauthorization.exceptions.ObjectNotDeletedException;
import com.fabio.authenticationauthorization.exceptions.ObjectNotFoundException;
import com.fabio.authenticationauthorization.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(NewCustomerDto dto){
        Customer customer = toCustomer(dto);
        customer.setSenha(passwordEncoder.encode(customer.getSenha()));
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

    public Customer toCustomer(NewCustomerDto dto){
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setNome(dto.getNome());
        customer.setEmail(dto.getEmail());
        customer.setSenha(dto.getSenha());

        return customer;
    }
}
