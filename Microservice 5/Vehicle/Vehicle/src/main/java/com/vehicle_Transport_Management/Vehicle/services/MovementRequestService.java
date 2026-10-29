package com.vehicle_Transport_Management.Vehicle.services;

import com.vehicle_Transport_Management.Vehicle.entity.MovementRequest;
import java.util.List;
import java.util.Optional;

public interface MovementRequestService {
    MovementRequest createRequest(MovementRequest request);
    List<MovementRequest> getAllRequests();
    Optional<MovementRequest> getRequestById(Long id);
    MovementRequest updateRequestStatus(Long id, String status);
}