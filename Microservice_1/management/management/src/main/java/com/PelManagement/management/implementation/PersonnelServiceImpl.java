package com.PelManagement.management.implementation;

import com.PelManagement.management.entity.Personnel;
import com.PelManagement.management.repositories.PersonnelRepository;
import com.PelManagement.management.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Override
    public Personnel createPersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public Optional<Personnel> getPersonnelById(Long id) {
        return personnelRepository.findById(id);
    }

    @Override
    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }

    @Override
    public Personnel updatePersonnel(Long id, Personnel personnelDetails) {
        Personnel personnel = personnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personnel not found with id: " + id));

        personnel.setEmployeeCode(personnelDetails.getEmployeeCode());
        personnel.setFullName(personnelDetails.getFullName());
        personnel.setRole(personnelDetails.getRole());
        personnel.setUnitName(personnelDetails.getUnitName());
        personnel.setPosting(personnelDetails.getPosting());
        personnel.setAvailabilityStatus(personnelDetails.getAvailabilityStatus());
        personnel.setActive(personnelDetails.getActive());

        return personnelRepository.save(personnel);
    }

    @Override
    public void deletePersonnel(Long id) {
        personnelRepository.deleteById(id);
    }
}