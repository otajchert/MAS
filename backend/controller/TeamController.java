package com.zbyszkobud.controller;

import com.zbyszkobud.model.Team;
import com.zbyszkobud.service.ApplicationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private ApplicationFacade applicationFacade;

    @GetMapping
    public List<Team> getAllTeams() {
        return applicationFacade.getTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = applicationFacade.getTeams().stream()
                                     .filter(t -> t.getId().equals(id))
                                     .findFirst()
                                     .orElse(null);
        if (team != null) {
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        applicationFacade.addTeam(team);
        return team;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team teamDetails) {
        Team team = applicationFacade.getTeams().stream()
                                     .filter(t -> t.getId().equals(id))
                                     .findFirst()
                                     .orElse(null);
        if (team != null) {
            team.setName(teamDetails.getName());
            team.setCreationDate(teamDetails.getCreationDate());
            team.setEmployees(teamDetails.getEmployees());
            team.setManager(teamDetails.getManager());
            team.setProjects(teamDetails.getProjects());
            applicationFacade.updateTeam(team);
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean deleted = applicationFacade.deleteTeam(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
