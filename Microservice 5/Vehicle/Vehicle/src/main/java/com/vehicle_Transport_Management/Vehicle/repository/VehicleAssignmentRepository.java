package com.vehicle_Transport_Management.Vehicle.repository;

import com.vehicle_Transport_Management.Vehicle.entity.VehicleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleAssignmentRepository extends JpaRepository<VehicleAssignment, Long> {
    List<VehicleAssignment> findByVehicleId(Long vehicleId);
    List<VehicleAssignment> findByDriverId(Long driverId);
    List<VehicleAssignment> findByStatus(String status);
}