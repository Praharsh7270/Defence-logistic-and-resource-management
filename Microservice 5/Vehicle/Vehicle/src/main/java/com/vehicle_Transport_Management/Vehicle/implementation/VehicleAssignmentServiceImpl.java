package com.vehicle_Transport_Management.Vehicle.implementation;

import com.vehicle_Transport_Management.Vehicle.services.VehicleAssignmentService;
import com.vehicle_Transport_Management.Vehicle.entity.VehicleAssignment; // Agar entity ka naam capital V hai toh yehi rakhein
import com.vehicle_Transport_Management.Vehicle.repository.VehicleAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleAssignmentServiceImpl implements VehicleAssignmentService {

    @Autowired
    private VehicleAssignmentRepository assignmentRepository;

    @Override
    public VehicleAssignment assignVehicle(VehicleAssignment assignment) {
        assignment.setAssignedAt(LocalDateTime.now());
        assignment.setStatus("ACTIVE");
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<VehicleAssignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public Optional<VehicleAssignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    @Override
    public VehicleAssignment returnVehicle(Long id) {
        VehicleAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));

        assignment.setReturnedAt(LocalDateTime.now());
        assignment.setStatus("COMPLETED");
        return assignmentRepository.save(assignment);
    }
}