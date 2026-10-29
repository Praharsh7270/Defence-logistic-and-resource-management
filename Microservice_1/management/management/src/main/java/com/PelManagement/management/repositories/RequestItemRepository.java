package com.PelManagement.management.repositories;

import com.PelManagement.management.entity.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {
    // Helpful query to fetch all items associated with a specific request ID
    List<RequestItem> findByRequestId(Long requestId);
}