package com.InventoryService.Inventory.controller;

import com.InventoryService.Inventory.entity.InventoryIssue;
import com.InventoryService.Inventory.Services.inventory_issues;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-issues")
public class InventoryIssueController {

    private final inventory_issues inventoryIssueService;

    // Constructor Injection
    public InventoryIssueController(inventory_issues inventoryIssueService) {
        this.inventoryIssueService = inventoryIssueService;
    }

    // 1. Item issue karne ke liye (POST API)
    // URL example: POST /api/inventory-issues/issue?inventoryId=1&personnelId=2&quantity=5&purpose=Training
    @PostMapping("/issue")
    public ResponseEntity<InventoryIssue> issueItem(
            @RequestParam Long inventoryId,
            @RequestParam Long personnelId,
            @RequestParam int quantity,
            @RequestParam String purpose) {

        InventoryIssue issue = inventoryIssueService.issueItem(inventoryId, personnelId, quantity, purpose);
        return ResponseEntity.ok(issue);
    }


    @PutMapping("/return/{issueId}")
    public ResponseEntity<String> returnItem(
            @PathVariable Long issueId,
            @RequestParam int returnQuantity) {

        inventoryIssueService.returnItem(issueId, returnQuantity);
        return ResponseEntity.ok("Item successfully return ho gaya aur inventory stock update ho gaya!");
    }


    @GetMapping("/personnel/{personnelId}")
    public ResponseEntity<List<InventoryIssue>> getIssuesByPersonnel(@PathVariable Long personnelId) {
        List<InventoryIssue> issues = inventoryIssueService.getIssuesByPersonnel(personnelId);
        return ResponseEntity.ok(issues);
    }
}