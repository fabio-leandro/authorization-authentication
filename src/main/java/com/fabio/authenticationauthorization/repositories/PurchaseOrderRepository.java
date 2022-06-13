package com.fabio.authenticationauthorization.repositories;

import com.fabio.authenticationauthorization.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findAllByCustomerId(Long id);

}
