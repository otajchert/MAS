package com.zbyszkobud.repository;

import com.zbyszkobud.model.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    private List<Client> clients = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private List<Equipment> equipment = new ArrayList<>();
    private List<ProjectEquipment> projectEquipment = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Invoice> invoices = new ArrayList<>();

    public List<Client> getClients() {
        return clients;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }
    public List<ProjectEquipment> getProjectEquipments() {
        return projectEquipment;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
