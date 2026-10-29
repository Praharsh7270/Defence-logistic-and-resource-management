package com.vehicle_Transport_Management.Vehicle.controller;

import com.vehicle_Transport_Management.Vehicle.entity.VehicleAssignment;
import com.vehicle_Transport_Management.Vehicle.services.VehicleAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class VehicleAssignmentController {

    @Autowired
    private VehicleAssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<VehicleAssignment> assignVehicle(@RequestBody VehicleAssignment assignment) {
        return ResponseEntity.ok(assignmentService.assignVehicle(assignment));
    }

    @GetMapping
    public ResponseEntity<List<VehicleAssignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleAssignment> getAssignmentById(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<VehicleAssignment> returnVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.returnVehicle(id));
    }
}