package com.PelManagement.management.implementation;

import com.PelManagement.management.entity.Equipment;
import com.PelManagement.management.repositories.EquipmentRepository;
import com.PelManagement.management.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment updateEquipment(Long id, Equipment equipmentDetails) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found with id: " + id));

        equipment.setAssetCode(equipmentDetails.getAssetCode());
        equipment.setName(equipmentDetails.getName());
        equipment.setCategory(equipmentDetails.getCategory());
        equipment.setLocation(equipmentDetails.getLocation());
        equipment.setOwnershipUnit(equipmentDetails.getOwnershipUnit());
        equipment.setStatus(equipmentDetails.getStatus());
        equipment.setConditionStatus(equipmentDetails.getConditionStatus());

        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}