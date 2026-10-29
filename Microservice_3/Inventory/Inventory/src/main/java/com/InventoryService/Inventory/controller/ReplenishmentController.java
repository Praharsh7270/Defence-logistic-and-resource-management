package com.InventoryService.Inventory.controller;

import com.InventoryService.Inventory.entity.RequestItem;
import com.InventoryService.Inventory.Services.ReplenishmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replenishment")
public class ReplenishmentController {

    private final ReplenishmentService replenishmentService;

    // Constructor Injection
    public ReplenishmentController(ReplenishmentService replenishmentService) {
        this.replenishmentService = replenishmentService;
    }

    // 1. Low stock check karke requests trigger karne ke liye (POST API)
    // URL: POST /api/replenishment/check-low-stock
    @PostMapping("/check-low-stock")
    public ResponseEntity<String> checkLowStock() {
        replenishmentService.checkLowStockAndGenerateRequests();
        return ResponseEntity.ok("Low stock check successfully run ho gaya!");
    }

    // 2. Nayi replenishment request create karne ke liye (POST API)
    // URL example: POST /api/replenishment/request?inventoryId=1&requestedQuantity=50&priority=HIGH
    @PostMapping("/request")
    public ResponseEntity<String> createReplenishmentRequest(
            @RequestParam Long inventoryId,
            @RequestParam int requestedQuantity,
            @RequestParam String priority) {

        replenishmentService.createReplenishmentRequest(inventoryId, requestedQuantity, priority);
        return ResponseEntity.ok("Replenishment request successfully create ho gayi!");
    }

    // 3. Request ka status update karne ke liye (PUT API)
    // URL example: PUT /api/replenishment/status/1?status=APPROVED
    @PutMapping("/status/{requestId}")
    public ResponseEntity<String> updateRequestStatus(
            @PathVariable Long requestId,
            @RequestParam String status) {

        replenishmentService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok("Replenishment request status successfully update ho gaya!");
    }

    // 4. Saari pending/all replenishment requests nikalne ke liye (GET API)
    // URL: GET /api/replenishment/pending
    @GetMapping("/pending")
    public ResponseEntity<List<RequestItem>> getPendingRequests() {
        List<RequestItem> requests = replenishmentService.getPendingReplenishmentRequests();
        return ResponseEntity.ok(requests);
    }
}