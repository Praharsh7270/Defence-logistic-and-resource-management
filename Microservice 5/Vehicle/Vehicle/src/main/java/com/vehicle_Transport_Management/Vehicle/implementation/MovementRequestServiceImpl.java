package com.vehicle_Transport_Management.Vehicle.implementation;

import com.vehicle_Transport_Management.Vehicle.services.MovementRequestService;
import com.vehicle_Transport_Management.Vehicle.entity.MovementRequest; // Agar entity ka naam capital M hai toh yehi rakhein
import com.vehicle_Transport_Management.Vehicle.repository.MovementRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovementRequestServiceImpl implements MovementRequestService {

    @Autowired
    private MovementRequestRepository requestRepository;

    @Override
    public MovementRequest createRequest(MovementRequest request) {
        request.setRequestDate(LocalDateTime.now());
        request.setStatus("PENDING");
        return requestRepository.save(request);
    }

    @Override
    public List<MovementRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Optional<MovementRequest> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public MovementRequest updateRequestStatus(Long id, String status) {
        MovementRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movement Request not found with id: " + id));

        request.setStatus(status);
        return requestRepository.save(request);
    }
}