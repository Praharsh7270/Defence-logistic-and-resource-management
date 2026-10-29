package com.vehicle_Transport_Management.Vehicle.implementation;

import com.vehicle_Transport_Management.Vehicle.services.VehicleService;
import com.vehicle_Transport_Management.Vehicle.entity.vehicle;
import com.vehicle_Transport_Management.Vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public vehicle saveVehicle(vehicle veh) {
        return vehicleRepository.save(veh);
    }

    @Override
    public List<vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Optional<vehicle> getVehicleByRegistrationNo(String registrationNo) {
        return vehicleRepository.findByRegistrationNo(registrationNo);
    }

    @Override
    public vehicle updateVehicle(Long id, vehicle vehicleDetails) {
        vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        existingVehicle.setRegistrationNo(vehicleDetails.getRegistrationNo());
        existingVehicle.setVehicleType(vehicleDetails.getVehicleType());
        existingVehicle.setDriverId(vehicleDetails.getDriverId());
        existingVehicle.setLocation(vehicleDetails.getLocation());
        existingVehicle.setStatus(vehicleDetails.getStatus());
        existingVehicle.setConditionStatus(vehicleDetails.getConditionStatus());

        return vehicleRepository.save(existingVehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<vehicle> getVehiclesByStatus(String status) {
        return vehicleRepository.findByStatus(status);
    }
}