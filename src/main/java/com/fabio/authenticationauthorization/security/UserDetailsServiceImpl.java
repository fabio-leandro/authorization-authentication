package com.fabio.authenticationauthorization.security;

import com.fabio.authenticationauthorization.domain.Customer;
import com.fabio.authenticationauthorization.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(customer.getId().intValue(),customer.getEmail(),customer.getSenha(),customer.getPerfis());
    }
}
