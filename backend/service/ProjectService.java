package com.zbyszkobud.service;

import com.zbyszkobud.model.Equipment;
import com.zbyszkobud.model.Project;
import com.zbyszkobud.model.ProjectEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ApplicationFacade applicationFacade;

    public List<Project> getAllProjects() {
        return applicationFacade.getProjects();
    }

    public Project getProjectById(Long id) {
        return applicationFacade.getProjects().stream()
            .filter(project -> project.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public Project saveProject(Project project) {
        applicationFacade.addProject(project);
        return project;
    }

    public void deleteProject(Long id) {
        applicationFacade.deleteProject(id);
    }

    public Set<ProjectEquipment> getProjectEquipment(Long projectId) {
        Project project = getProjectById(projectId);
        return project != null ? project.getProjectEquipments() : null;
    }
    
    public List<Project> getProjectsByManagerId(Long managerId) {
        return applicationFacade.getProjects().stream()
            .filter(project -> project.getTeam() != null && project.getTeam().getManager() != null)
            .filter(project -> project.getTeam().getManager().getId().equals(managerId))
            .collect(Collectors.toList());
    }

    public boolean addEquipmentToProject(Long projectId, Long equipmentId) {
        Project project = applicationFacade.getProjectById(projectId);
        Equipment equipment = applicationFacade.getEquipmentById(equipmentId);
    
        System.out.println("Project: " + project);
        System.out.println("Equipment: " + equipment);
    
        if (project != null && equipment != null) {
            ProjectEquipment projectEquipment = new ProjectEquipment();
            projectEquipment.setProject(project);
            projectEquipment.setEquipment(equipment);
            project.getProjectEquipments().add(projectEquipment);
            applicationFacade.saveProject(project);
            return true;
        } else {
            System.out.println("Project albo equipment to null");
        }
    
        return false;
    }
    
}
