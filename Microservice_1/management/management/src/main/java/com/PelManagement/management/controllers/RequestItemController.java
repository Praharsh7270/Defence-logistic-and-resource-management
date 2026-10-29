package com.PelManagement.management.controllers;

import com.PelManagement.management.entity.RequestItem;
import com.PelManagement.management.services.RequestItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request-items")
public class RequestItemController {

    @Autowired
    private RequestItemService requestItemService;

    @PostMapping
    public ResponseEntity<RequestItem> addRequestItem(@RequestBody RequestItem requestItem) {
        return ResponseEntity.ok(requestItemService.addRequestItem(requestItem));
    }

    @GetMapping("/request/{requestId}")
    public ResponseEntity<List<RequestItem>> getItemsByRequestId(@PathVariable Long requestId) {
        return ResponseEntity.ok(requestItemService.getItemsByRequestId(requestId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestItem> getRequestItemById(@PathVariable Long id) {
        return requestItemService.getRequestItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestItem> updateRequestItem(@PathVariable Long id, @RequestBody RequestItem requestItem) {
        try {
            return ResponseEntity.ok(requestItemService.updateRequestItem(id, requestItem));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequestItem(@PathVariable Long id) {
        requestItemService.deleteRequestItem(id);
        return ResponseEntity.ok().build();
    }
}