package com.fabio.authenticationauthorization.services;

import com.fabio.authenticationauthorization.domain.PurchaseOrder;
import com.fabio.authenticationauthorization.exceptions.AuthorizationException;
import com.fabio.authenticationauthorization.exceptions.ObjectNotFoundException;
import com.fabio.authenticationauthorization.repositories.PurchaseOrderRepository;
import com.fabio.authenticationauthorization.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository orderRepository;

    public PurchaseOrder saveOrder(PurchaseOrder order){
        return orderRepository.save(order);
    }

    public List<PurchaseOrder> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<PurchaseOrder> getAllCustomerId(Long id){
        UserSS user = UserService.authenticated();
        if (user == null || user.getId().longValue() != id){
            throw new AuthorizationException("Acesso Negado");
        }
        return orderRepository.findAllByCustomerId(id);
    }


    public PurchaseOrder getById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Order not found with id -> "+id));
    }

    public PurchaseOrder update(Long id, PurchaseOrder order){
        getById(id);
        return orderRepository.save(order);
    }

    public void delete(Long id){
        getById(id);
        orderRepository.deleteById(id);
    }



}
