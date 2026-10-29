package com.PelManagement.management.repositories;

import com.PelManagement.management.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    // Custom query methods can be added here if needed, e.g.:
    // List<Personnel> findByUnitName(String unitName);
}