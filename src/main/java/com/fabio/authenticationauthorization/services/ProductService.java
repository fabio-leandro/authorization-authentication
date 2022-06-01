package com.fabio.authenticationauthorization.services;

import com.fabio.authenticationauthorization.domain.Product;
import com.fabio.authenticationauthorization.exceptions.ObjectNotDeletedException;
import com.fabio.authenticationauthorization.exceptions.ObjectNotFoundException;
import com.fabio.authenticationauthorization.repositories.ProductRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Product not found with id -> "+id));
    }

    public Product update(Long id, Product product){
        findById(id);
        return productRepository.save(product);
    }

    public void delete(Long id){
        findById(id);
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectNotDeletedException("The product cannot be delete." +
                    " It might was used in another object.");
        }
    }
}
