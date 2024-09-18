package com.zbyszkobud.service;

import com.zbyszkobud.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private ApplicationFacade applicationFacade;

    public List<Team> getAllTeams() {
        return applicationFacade.getTeams();
    }

    public Team getTeamById(Long id) {
        return applicationFacade.getTeams().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Team createTeam(Team team) {
        applicationFacade.addTeam(team);
        return team;
    }

    public Team updateTeam(Long id, Team teamDetails) {
        Team team = getTeamById(id);
        if (team != null) {
            team.setName(teamDetails.getName());
            team.setCreationDate(teamDetails.getCreationDate());
            team.setEmployees(teamDetails.getEmployees());
            team.setManager(teamDetails.getManager());
            team.setProjects(teamDetails.getProjects());
            applicationFacade.updateTeam(team);
        }
        return team;
    }

    public boolean deleteTeam(Long id) {
        return applicationFacade.deleteTeam(id);
    }
}
