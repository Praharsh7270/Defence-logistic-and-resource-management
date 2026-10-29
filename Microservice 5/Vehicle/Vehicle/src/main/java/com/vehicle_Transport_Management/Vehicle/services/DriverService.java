package com.vehicle_Transport_Management.Vehicle.services;

import com.vehicle_Transport_Management.Vehicle.entity.drivers;
import java.util.List;
import java.util.Optional;

public interface DriverService {
    drivers saveDriver(drivers driver);
    List<drivers> getAllDrivers();
    Optional<drivers> getDriverById(Long id);
    Optional<drivers> getDriverByLicenseNumber(String licenseNumber);
    drivers updateDriver(Long id, drivers driver);
    void deleteDriver(Long id);
    List<drivers> getDriversByStatus(String status);
}