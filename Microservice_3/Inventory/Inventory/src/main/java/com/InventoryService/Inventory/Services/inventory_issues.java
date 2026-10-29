package com.InventoryService.Inventory.Services;

import com.InventoryService.Inventory.entity.InventoryIssue;
import java.util.List;

public interface inventory_issues {

    InventoryIssue issueItem(Long inventoryId, Long personnelId, int quantity, String purpose);

    void returnItem(Long issueId, int returnQuantity);

    List<InventoryIssue> getIssuesByPersonnel(Long personnelId);
}