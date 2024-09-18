package com.zbyszkobud.service;

import com.zbyszkobud.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private ApplicationFacade applicationFacade;

    public List<Equipment> getAllEquipment() {
        return applicationFacade.getEquipment();
    }

    public Equipment getEquipmentById(Long id) {
        return applicationFacade.getEquipment().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Equipment createEquipment(Equipment equipment) {
        applicationFacade.addEquipment(equipment);
        return equipment;
    }

    public Equipment updateEquipment(Long id, Equipment equipmentDetails) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            equipment.setName(equipmentDetails.getName());
            equipment.setManufacturer(equipmentDetails.getManufacturer());
            applicationFacade.updateEquipment(equipment);
        }
        return equipment;
    }

    public boolean deleteEquipment(Long id) {
        return applicationFacade.deleteEquipment(id);
    }
}
