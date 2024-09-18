package com.zbyszkobud.service;

//import com.zbyszkobud.model.*;
import com.zbyszkobud.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public void loadData(InMemoryDatabase inMemoryDatabase) {
        inMemoryDatabase.getClients().addAll(clientRepository.findAll());
        inMemoryDatabase.getEmployees().addAll(employeeRepository.findAll());
        inMemoryDatabase.getTeams().addAll(teamRepository.findAll());
        inMemoryDatabase.getProjects().addAll(projectRepository.findAll());
        inMemoryDatabase.getEquipment().addAll(equipmentRepository.findAll());
        inMemoryDatabase.getOrders().addAll(orderRepository.findAll());
        inMemoryDatabase.getInvoices().addAll(invoiceRepository.findAll());
    }

    public void saveData(InMemoryDatabase inMemoryDatabase) {
        clientRepository.saveAll(inMemoryDatabase.getClients());
        employeeRepository.saveAll(inMemoryDatabase.getEmployees());
        teamRepository.saveAll(inMemoryDatabase.getTeams());
        projectRepository.saveAll(inMemoryDatabase.getProjects());
        equipmentRepository.saveAll(inMemoryDatabase.getEquipment());
        orderRepository.saveAll(inMemoryDatabase.getOrders());
        invoiceRepository.saveAll(inMemoryDatabase.getInvoices());
    }
}
