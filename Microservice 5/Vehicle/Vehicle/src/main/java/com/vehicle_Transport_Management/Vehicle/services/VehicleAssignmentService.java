package com.vehicle_Transport_Management.Vehicle.services;

import com.vehicle_Transport_Management.Vehicle.entity.VehicleAssignment;
import java.util.List;
import java.util.Optional;

public interface VehicleAssignmentService {
    VehicleAssignment assignVehicle(VehicleAssignment assignment);
    List<VehicleAssignment> getAllAssignments();
    Optional<VehicleAssignment> getAssignmentById(Long id);
    VehicleAssignment returnVehicle(Long id);
}