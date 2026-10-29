package com.vehicle_Transport_Management.Vehicle.services;

import com.vehicle_Transport_Management.Vehicle.entity.vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    vehicle saveVehicle(vehicle vehicle);
    List<vehicle> getAllVehicles();
    Optional<vehicle> getVehicleById(Long id);
    Optional<vehicle> getVehicleByRegistrationNo(String registrationNo);
    vehicle updateVehicle(Long id, vehicle vehicle);
    void deleteVehicle(Long id);
    List<vehicle> getVehiclesByStatus(String status);
}