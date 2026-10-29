package com.PelManagement.management.services;

import com.PelManagement.management.entity.LogisticsRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LogisticsRequestService {
    LogisticsRequest createLogisticsRequest(LogisticsRequest request);
    LogisticsRequest createFromPayload(Map<String, Object> payload);
    Optional<LogisticsRequest> getLogisticsRequestById(Long id);
    List<LogisticsRequest> getAllLogisticsRequests();
    LogisticsRequest updateRequestStatus(Long id, String status);
    void deleteLogisticsRequest(Long id);
}