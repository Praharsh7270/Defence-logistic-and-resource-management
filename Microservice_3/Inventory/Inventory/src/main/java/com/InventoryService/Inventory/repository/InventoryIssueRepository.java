package com.InventoryService.Inventory.repository;

import com.InventoryService.Inventory.entity.InventoryIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryIssueRepository extends JpaRepository<InventoryIssue, Long> {

    // Custom method: Kisi specific inventory item ke saare issues nikalne ke liye
    List<InventoryIssue> findByInventoryId(Long inventoryId);

    // Custom method: Kisi specific request id ke basis par issues dekhne ke liye
    List<InventoryIssue> findByRequestId(Long requestId);

}