package com.zbyszkobud.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Double budget;
    private boolean accepted;
    private String status;
    private String name;
    

  @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "team_id")
     @JsonBackReference
    private Team team;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private Set<ProjectEquipment> projectEquipments = new HashSet<>();

    // metoda klasowa
    public static String getStatusDescription(String status) {
        switch (status) {
            case "NOT_STARTED":
                return "Not Started";
            case "IN_PROGRESS":
                return "In Progress";
            case "COMPLETED":
                return "Completed";
            default:
                return "Unknown Status";
        }
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Team getTeam() {
        return team;
    }
    
    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProjectEquipment> getProjectEquipments() {
        return projectEquipments;
    }

    public void setProjectEquipments(Set<ProjectEquipment> projectEquipments) {
        this.projectEquipments = projectEquipments;
    }

  
}