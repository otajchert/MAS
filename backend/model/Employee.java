package com.zbyszkobud.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
public class Employee extends Person {
    private Date hireDate;
    private String specialization; //atrybut opcjonalny
    private Double salary;//ograniczenie atrybutu
    private boolean isManager;//ograniczenie xor
    private boolean isWorker;
    private Integer completedProjects;
    private Boolean isExperienced;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference 
    private Team team;

    @OneToMany(mappedBy = "manager")
    @JsonManagedReference
  
    private List<Team> managedTeams;

    // Atrybut pochodny
   public int getYearsOfExperience() {
    if (hireDate == null) {
        return 0; 
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(hireDate);
    int hireYear = calendar.get(Calendar.YEAR);
    
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    return currentYear - hireYear;
}

    // Gettery i settery
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        if (salary != null && salary >= 0) {
            this.salary = salary;
        } else {
            throw new IllegalArgumentException("nie moze byc ujemne");
        }
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
        if (isManager) {
            this.isWorker = false;
            if (this.completedProjects != null && this.completedProjects >= 10) {
                this.isExperienced = true;
            } else {
                this.isExperienced = false;
            }
        } else {
            this.isExperienced = null;
        }
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean isWorker) {
        this.isWorker = isWorker;
        if (isWorker) {
            this.isManager = false;
            this.isExperienced = null;
        }
    }

    public Integer getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(Integer completedProjects) {
        if (completedProjects != null && completedProjects >= 0) {
            this.completedProjects = completedProjects;
        } else {
            throw new IllegalArgumentException("nie moze byc ujemne");
        }
    }

    public Boolean isExperienced() {
        return isExperienced;
    }

    public void setExperienced(Boolean isExperienced) {
        this.isExperienced = isExperienced;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Team> getManagedTeams() {
        return managedTeams;
    }

    public void setManagedTeams(List<Team> managedTeams) {
        this.managedTeams = managedTeams;
    }
}
