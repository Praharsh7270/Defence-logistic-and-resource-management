package com.InventoryService.Inventory.implementation;

import com.InventoryService.Inventory.entity.Inventory;
import com.InventoryService.Inventory.repository.InventoryRepository;
import com.InventoryService.Inventory.Services.InventoryService;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    // Constructor Injection
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getStockById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item is ID ke sath nahi mila: " + id));
    }

    @Override
    public void updateStockQuantity(Long id, int newQuantity) {
        Inventory inventory = getStockById(id);
        inventory.setQuantity(newQuantity);
        inventory.setUpdatedAt(OffsetDateTime.now());
        inventoryRepository.save(inventory);
    }
}