package com.PelManagement.management.services;

import com.PelManagement.management.entity.Equipment;
import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment);
    Optional<Equipment> getEquipmentById(Long id);
    List<Equipment> getAllEquipment();
    Equipment updateEquipment(Long id, Equipment equipment);
    void deleteEquipment(Long id);
}