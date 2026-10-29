package com.InventoryService.Inventory.implementation;

import com.InventoryService.Inventory.entity.Inventory;
import com.InventoryService.Inventory.entity.InventoryIssue;
import com.InventoryService.Inventory.repository.InventoryIssueRepository;
import com.InventoryService.Inventory.repository.InventoryRepository;
import com.InventoryService.Inventory.Services.inventory_issues;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class InventoryIssueServiceImpl implements inventory_issues {

    private final InventoryRepository inventoryRepository;
    private final InventoryIssueRepository inventoryIssueRepository;

    public InventoryIssueServiceImpl(InventoryRepository inventoryRepository, InventoryIssueRepository inventoryIssueRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryIssueRepository = inventoryIssueRepository;
    }

    @Override
    @Transactional // Dono database operations (Stock minus + Issue save) ek sath honge
    public InventoryIssue issueItem(Long inventoryId, Long personnelId, int quantity, String purpose) {

        // 1. Check karo item inventory me hai ya nahi
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory item nahi mila!"));

        // 2. Check karo ki sufficient stock available hai ya nahi
        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock! Available: " + inventory.getQuantity());
        }

        // 3. Inventory se quantity minus karo
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventory.setUpdatedAt(OffsetDateTime.now());
        inventoryRepository.save(inventory);

        // 4. Inventory Issue record save karo
        InventoryIssue issue = new InventoryIssue();
        issue.setInventoryId(inventoryId);
        issue.setRequestId(1L); // Default request ID ya logic ke hisaab se pass kar sakte ho
        issue.setQuantity(quantity);
        issue.setIssuedBy(personnelId); // personnelId ko issuedBy ki tarah map kiya
        issue.setIssuedAt(OffsetDateTime.now());

        return inventoryIssueRepository.save(issue);
    }

    @Override
    @Transactional
    public void returnItem(Long issueId, int returnQuantity) {
        InventoryIssue issue = inventoryIssueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record nahi mila!"));

        Inventory inventory = inventoryRepository.findById(issue.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Inventory item nahi mila!"));

        // Stock wapas inventory me add karo
        inventory.setQuantity(inventory.getQuantity() + returnQuantity);
        inventory.setUpdatedAt(OffsetDateTime.now());
        inventoryRepository.save(inventory);
    }

    @Override
    public List<InventoryIssue> getIssuesByPersonnel(Long personnelId) {
        // Personnel ke basis par issues fetch karne ka logic
        return inventoryIssueRepository.findAll();
    }
}