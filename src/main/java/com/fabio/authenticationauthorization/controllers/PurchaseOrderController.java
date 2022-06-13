package com.fabio.authenticationauthorization.controllers;

import com.fabio.authenticationauthorization.domain.PurchaseOrder;
import com.fabio.authenticationauthorization.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<PurchaseOrder> saveOrder(@RequestBody PurchaseOrder order){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.saveOrder(order));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllOrders(){
        return ResponseEntity.ok(purchaseOrderService.getAllOrders());
    }

    @GetMapping("/customerOrders/{id}")
    public ResponseEntity<List<PurchaseOrder>> getAllByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(purchaseOrderService.getAllCustomerId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getById(@PathVariable Long id){
        return ResponseEntity.ok(purchaseOrderService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> update(@PathVariable Long id, @RequestBody PurchaseOrder order){
        return ResponseEntity.ok(purchaseOrderService.update(id,order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        purchaseOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
