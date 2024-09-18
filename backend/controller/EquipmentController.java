package com.zbyszkobud.controller;

import com.zbyszkobud.model.Equipment;
import com.zbyszkobud.service.ApplicationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private ApplicationFacade applicationFacade;

    @GetMapping("/all")
    public List<Equipment> getAllEquipment() {
        return applicationFacade.getEquipment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Equipment equipment = applicationFacade.getEquipment().stream()
                                               .filter(e -> e.getId().equals(id))
                                               .findFirst()
                                               .orElse(null);
        if (equipment != null) {
            return ResponseEntity.ok(equipment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        applicationFacade.addEquipment(equipment);
        return equipment;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment equipmentDetails) {
        Equipment equipment = applicationFacade.getEquipment().stream()
                                               .filter(e -> e.getId().equals(id))
                                               .findFirst()
                                               .orElse(null);
        if (equipment != null) {
            equipment.setName(equipmentDetails.getName());
            equipment.setManufacturer(equipmentDetails.getManufacturer());
            applicationFacade.updateEquipment(equipment);
            return ResponseEntity.ok(equipment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        boolean deleted = applicationFacade.deleteEquipment(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
