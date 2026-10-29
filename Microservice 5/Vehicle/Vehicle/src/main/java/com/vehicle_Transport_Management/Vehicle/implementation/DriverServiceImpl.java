package com.vehicle_Transport_Management.Vehicle.implementation;

import com.vehicle_Transport_Management.Vehicle.services.DriverService;
import com.vehicle_Transport_Management.Vehicle.entity.drivers;
import com.vehicle_Transport_Management.Vehicle.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public drivers saveDriver(drivers driver) {
        return driverRepository.save(driver);
    }

    @Override
    public List<drivers> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<drivers> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public Optional<drivers> getDriverByLicenseNumber(String licenseNumber) {
        return driverRepository.findByLicenseNumber(licenseNumber);
    }

    @Override
    public drivers updateDriver(Long id, drivers driverDetails) {
        drivers existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));

        existingDriver.setName(driverDetails.getName());
        existingDriver.setLicenseNumber(driverDetails.getLicenseNumber());
        existingDriver.setContactNumber(driverDetails.getContactNumber());
        existingDriver.setStatus(driverDetails.getStatus());
        existingDriver.setAssignedVehicleId(driverDetails.getAssignedVehicleId());

        return driverRepository.save(existingDriver);
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public List<drivers> getDriversByStatus(String status) {
        return driverRepository.findByStatus(status);
    }
}