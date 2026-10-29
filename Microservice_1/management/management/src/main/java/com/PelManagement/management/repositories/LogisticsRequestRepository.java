package com.PelManagement.management.repositories;

import com.PelManagement.management.entity.LogisticsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRequestRepository extends JpaRepository<LogisticsRequest, Long> {
}