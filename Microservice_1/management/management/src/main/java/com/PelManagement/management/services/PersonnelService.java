package com.PelManagement.management.services;

import com.PelManagement.management.entity.Personnel;
import java.util.List;
import java.util.Optional;

public interface PersonnelService {
    Personnel createPersonnel(Personnel personnel);
    Optional<Personnel> getPersonnelById(Long id);
    List<Personnel> getAllPersonnel();
    Personnel updatePersonnel(Long id, Personnel personnel);
    void deletePersonnel(Long id);
}