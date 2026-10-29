package com.vehicle_Transport_Management.Vehicle.controller;

import com.vehicle_Transport_Management.Vehicle.entity.MovementRequest;
import com.vehicle_Transport_Management.Vehicle.services.MovementRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/movement-requests")
public class MovementRequestController {

    @Autowired
    private MovementRequestService requestService;

    @PostMapping
    public ResponseEntity<MovementRequest> createRequest(@RequestBody MovementRequest request) {
        return ResponseEntity.ok(requestService.createRequest(request));
    }

    @GetMapping
    public ResponseEntity<List<MovementRequest>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementRequest> getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MovementRequest> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(requestService.updateRequestStatus(id, status));
    }
}