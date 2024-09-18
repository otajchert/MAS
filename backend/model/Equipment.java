package com.zbyszkobud.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manufacturer;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<ProjectEquipment> projectEquipments;

   // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<ProjectEquipment> getProjectEquipments() {
        return projectEquipments;
    }

    public void setProjectEquipments(List<ProjectEquipment> projectEquipments) {
        this.projectEquipments = projectEquipments;
    }
}