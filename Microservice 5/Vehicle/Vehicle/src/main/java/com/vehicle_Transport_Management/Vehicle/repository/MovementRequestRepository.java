package com.vehicle_Transport_Management.Vehicle.repository;

import com.vehicle_Transport_Management.Vehicle.entity.MovementRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovementRequestRepository extends JpaRepository<MovementRequest, Long> {
    List<MovementRequest> findByStatus(String status);
    List<MovementRequest> findByRequestedBy(String requestedBy);
}