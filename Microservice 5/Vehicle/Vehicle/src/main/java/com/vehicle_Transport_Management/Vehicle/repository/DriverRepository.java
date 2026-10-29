package com.vehicle_Transport_Management.Vehicle.repository;

import com.vehicle_Transport_Management.Vehicle.entity.drivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<drivers, Long> {
    Optional<drivers> findByLicenseNumber(String licenseNumber);
    List<drivers> findByStatus(String status);
}