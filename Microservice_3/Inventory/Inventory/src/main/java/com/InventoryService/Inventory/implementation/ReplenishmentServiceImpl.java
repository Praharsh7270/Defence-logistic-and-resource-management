package com.InventoryService.Inventory.implementation;

import com.InventoryService.Inventory.entity.Inventory;
import com.InventoryService.Inventory.entity.RequestItem;
import com.InventoryService.Inventory.repository.InventoryRepository;
import com.InventoryService.Inventory.repository.ReplenishmentRepository;
import com.InventoryService.Inventory.Services.ReplenishmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplenishmentServiceImpl implements ReplenishmentService {

    private final InventoryRepository inventoryRepository;
    private final ReplenishmentRepository requestItemRepository;

    public ReplenishmentServiceImpl(InventoryRepository inventoryRepository, ReplenishmentRepository requestItemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.requestItemRepository = requestItemRepository;
    }

    @Override
    public void checkLowStockAndGenerateRequests() {
        List<Inventory> inventories = inventoryRepository.findAll();

        for (Inventory item : inventories) {
            if (item.getQuantity() <= item.getReorderLevel()) {
                System.out.println("LOW STOCK ALERT: " + item.getItemName() + " (Qty: " + item.getQuantity() + ")");
            }
        }
    }

    @Override
    public void createReplenishmentRequest(Long inventoryId, int requestedQuantity, String priority) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Item nahi mila!"));

        RequestItem requestItem = new RequestItem();
        requestItem.setRequestId(1L); // Default or handled via logistics request
        requestItem.setResourceType("SUPPLY");
        requestItem.setQuantity(requestedQuantity);
        requestItem.setDescription("Replenishment for: " + inventory.getItemName() + " | Priority: " + priority);

        requestItemRepository.save(requestItem);
    }

    @Override
    public void updateRequestStatus(Long requestId, String status) {
        // Status update logic
    }

    @Override
    public List<RequestItem> getPendingReplenishmentRequests() {
        return requestItemRepository.findAll();
    }
}