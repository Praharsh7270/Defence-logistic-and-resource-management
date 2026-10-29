package com.PelManagement.management.controllers;

import com.PelManagement.management.entity.LogisticsRequest;
import com.PelManagement.management.services.LogisticsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*")
public class LogisticsRequestController {

    @Autowired
    private LogisticsRequestService logisticsRequestService;

    @PostMapping
    public ResponseEntity<LogisticsRequest> createLogisticsRequest(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(logisticsRequestService.createFromPayload(payload));
    }

    @GetMapping
    public ResponseEntity<List<LogisticsRequest>> getAllLogisticsRequests() {
        return ResponseEntity.ok(logisticsRequestService.getAllLogisticsRequests());
    }

    @GetMapping("/my")
    public ResponseEntity<List<LogisticsRequest>> getMyRequests() {
        // For now, return all requests; filter by user when auth is integrated
        return ResponseEntity.ok(logisticsRequestService.getAllLogisticsRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogisticsRequest> getLogisticsRequestById(@PathVariable Long id) {
        return logisticsRequestService.getLogisticsRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LogisticsRequest> updateRequestStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            return ResponseEntity.ok(logisticsRequestService.updateRequestStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/{action}")
    public ResponseEntity<LogisticsRequest> processAction(@PathVariable Long id, @PathVariable String action) {
        try {
            String newStatus = switch (action.toUpperCase()) {
                case "APPROVE" -> "APPROVED";
                case "REJECT" -> "REJECTED";
                case "ALLOCATE" -> "ALLOCATED";
                case "START", "IN_PROGRESS" -> "IN_PROGRESS";
                case "COMPLETE" -> "COMPLETED";
                case "CANCEL" -> "CANCELLED";
                default -> action.toUpperCase();
            };
            return ResponseEntity.ok(logisticsRequestService.updateRequestStatus(id, newStatus));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogisticsRequest(@PathVariable Long id) {
        logisticsRequestService.deleteLogisticsRequest(id);
        return ResponseEntity.ok().build();
    }
}