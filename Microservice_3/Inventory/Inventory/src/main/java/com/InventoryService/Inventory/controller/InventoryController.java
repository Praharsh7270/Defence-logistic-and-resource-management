package com.InventoryService.Inventory.controller;

import com.InventoryService.Inventory.entity.Inventory;
import com.InventoryService.Inventory.Services.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // 1. Saari inventory items dekhne ke liye (GET API)
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllItems() {
        List<Inventory> items = inventoryService.getAllInventoryItems();
        return ResponseEntity.ok(items);
    }

    // 2. ID ke hisaab se specific item nikalne ke liye (GET API)
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getItemById(@PathVariable Long id) {
        Inventory item = inventoryService.getStockById(id);
        return ResponseEntity.ok(item);
    }

    // 3. Stock quantity update karne ke liye (PUT API)
    @PutMapping("/{id}/quantity")
    public ResponseEntity<String> updateQuantity(@PathVariable Long id, @RequestParam int quantity) {
        inventoryService.updateStockQuantity(id, quantity);
        return ResponseEntity.ok("Inventory stock successfully update ho gaya!");
    }
}
