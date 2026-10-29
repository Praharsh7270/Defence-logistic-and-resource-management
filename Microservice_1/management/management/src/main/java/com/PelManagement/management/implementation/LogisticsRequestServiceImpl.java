package com.PelManagement.management.implementation;

import com.PelManagement.management.entity.LogisticsRequest;
import com.PelManagement.management.repositories.LogisticsRequestRepository;
import com.PelManagement.management.services.LogisticsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class LogisticsRequestServiceImpl implements LogisticsRequestService {

    @Autowired
    private LogisticsRequestRepository logisticsRequestRepository;

    @Override
    public LogisticsRequest createLogisticsRequest(LogisticsRequest request) {
        if (request.getRequestNumber() == null || request.getRequestNumber().isBlank()) {
            request.setRequestNumber("REQ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        if (request.getRequestedBy() == null || request.getRequestedBy().isBlank()) {
            request.setRequestedBy("SYSTEM");
        }
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            request.setStatus("PENDING");
        }
        return logisticsRequestRepository.save(request);
    }

    @Override
    public LogisticsRequest createFromPayload(Map<String, Object> payload) {
        LogisticsRequest request = new LogisticsRequest();

        // Generate a unique request number
        request.setRequestNumber("REQ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        // Map form fields to entity fields
        request.setPurpose(getStringValue(payload, "purpose"));
        request.setRequestedBy(getStringValue(payload, "requestedBy", "SYSTEM"));
        request.setRequestingUnit(getStringValue(payload, "requestingUnit",
                getStringValue(payload, "requesting_unit", "")));
        request.setStatus("PENDING");
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());

        return logisticsRequestRepository.save(request);
    }

    @Override
    public Optional<LogisticsRequest> getLogisticsRequestById(Long id) {
        return logisticsRequestRepository.findById(id);
    }

    @Override
    public List<LogisticsRequest> getAllLogisticsRequests() {
        return logisticsRequestRepository.findAll();
    }

    @Override
    public LogisticsRequest updateRequestStatus(Long id, String status) {
        LogisticsRequest request = logisticsRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logistics Request not found with id: " + id));

        request.setStatus(status);
        request.setUpdatedAt(LocalDateTime.now());
        return logisticsRequestRepository.save(request);
    }

    @Override
    public void deleteLogisticsRequest(Long id) {
        logisticsRequestRepository.deleteById(id);
    }

    private String getStringValue(Map<String, Object> payload, String key) {
        Object value = payload.get(key);
        return value != null ? value.toString() : null;
    }

    private String getStringValue(Map<String, Object> payload, String key, String defaultValue) {
        Object value = payload.get(key);
        return value != null && !value.toString().isBlank() ? value.toString() : defaultValue;
    }
}