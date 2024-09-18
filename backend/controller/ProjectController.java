package com.zbyszkobud.controller;
import com.zbyszkobud.model.ProjectEquipment;
//import com.zbyszkobud.model.Employee;
import com.zbyszkobud.model.Project;
import com.zbyszkobud.model.Equipment;
import com.zbyszkobud.service.ApplicationFacade;
import com.zbyszkobud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ApplicationFacade applicationFacade;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public Project saveProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @GetMapping("/{id}/equipment")
    public ResponseEntity<List<Equipment>> getProjectEquipment(@PathVariable Long id) {
        List<Equipment> equipment = projectService.getProjectEquipment(id).stream()
            .map(ProjectEquipment::getEquipment)
            .collect(Collectors.toList());

        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<Project>> getProjectsByManagerId(@PathVariable Long managerId) {
        List<Project> projects = projectService.getProjectsByManagerId(managerId);
        return ResponseEntity.ok(projects);
    }
   @PostMapping("/{projectId}/equipment")
public ResponseEntity<Void> addEquipmentToProject(
        @PathVariable Long projectId,
        @RequestBody Map<String, Long> payload) {

    Long equipmentId = payload.get("equipmentId");
    boolean success = applicationFacade.addEquipmentToProject(projectId, equipmentId);

    if (success) {
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    

}
