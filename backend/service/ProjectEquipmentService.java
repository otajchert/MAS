package com.zbyszkobud.service;

import com.zbyszkobud.model.ProjectEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Hibernate;

import javax.transaction.Transactional;

@Service
public class ProjectEquipmentService {

    @Autowired
    private ApplicationFacade applicationFacade;

    @Transactional
    public ProjectEquipment getProjectEquipmentById(Long id) {
        ProjectEquipment projectEquipment = applicationFacade.getProjectEquipments().stream()
            .filter(pe -> pe.getId().equals(id))
            .findFirst()
            .orElse(null);
        
        return projectEquipment;
    }

    @Transactional
    public void saveProjectEquipment(ProjectEquipment projectEquipment) {
        applicationFacade.addProjectEquipment(projectEquipment);
    }

    @Transactional
    public void deleteProjectEquipment(Long id) {
        applicationFacade.deleteProjectEquipment(id);
    }
}
