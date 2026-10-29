package com.InventoryService.Inventory.Services;

import com.InventoryService.Inventory.entity.Inventory;
import java.util.List;

public interface InventoryService {

    // Return type 'Inventory' Entity hona chahiye, interface nahi!
    List<Inventory> getAllInventoryItems();

    Inventory getStockById(Long id);

    void updateStockQuantity(Long id, int newQuantity);
}