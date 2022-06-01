package com.fabio.authenticationauthorization.repositories;

import com.fabio.authenticationauthorization.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
