package com.vehicle_Transport_Management.Vehicle.repository;

import com.vehicle_Transport_Management.Vehicle.entity.vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<vehicle, Long> {
    Optional<vehicle> findByRegistrationNo(String registrationNo);
    List<vehicle> findByStatus(String status);
    List<vehicle> findByVehicleType(String vehicleType);
}