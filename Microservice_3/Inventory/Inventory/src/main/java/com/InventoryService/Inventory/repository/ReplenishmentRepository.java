package com.InventoryService.Inventory.repository;

import com.InventoryService.Inventory.entity.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplenishmentRepository extends JpaRepository<RequestItem, Long> {

    // Custom method: Request ID ke basis par items fetch karne ke liye
    List<RequestItem> findByRequestId(Long requestId);

}