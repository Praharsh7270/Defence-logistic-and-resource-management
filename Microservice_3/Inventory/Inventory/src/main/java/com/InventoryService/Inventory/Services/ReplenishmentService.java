package com.InventoryService.Inventory.Services;

import com.InventoryService.Inventory.entity.RequestItem;
import java.util.List;

public interface ReplenishmentService {
    void checkLowStockAndGenerateRequests();
    void createReplenishmentRequest(Long inventoryId, int requestedQuantity, String priority);
    void updateRequestStatus(Long requestId, String status);
    List<RequestItem> getPendingReplenishmentRequests();
}