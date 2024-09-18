package com.zbyszkobud.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date availabilityFrom;
    private Date availabilityTo;
    private String workScopeDescription;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "order")
    private Project project;

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAvailabilityFrom() {
        return availabilityFrom;
    }

    public void setAvailabilityFrom(Date availabilityFrom) {
        this.availabilityFrom = availabilityFrom;
    }

    public Date getAvailabilityTo() {
        return availabilityTo;
    }

    public void setAvailabilityTo(Date availabilityTo) {
        this.availabilityTo = availabilityTo;
    }

    public String getWorkScopeDescription() {
        return workScopeDescription;
    }

    public void setWorkScopeDescription(String workScopeDescription) {
        this.workScopeDescription = workScopeDescription;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}