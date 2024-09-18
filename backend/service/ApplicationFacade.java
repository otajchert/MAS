package com.zbyszkobud.service;

import com.zbyszkobud.model.*;
import com.zbyszkobud.repository.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import javax.persistence.EntityTransaction;
//import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
//import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class ApplicationFacade {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProjectEquipmentRepository projectEquipmentRepository;

    @Autowired
    private DatabaseService databaseService;

    private InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

    @PersistenceContext
    private EntityManager entityManager;


    private void loadDataFromDatabase() {
        inMemoryDatabase.getClients().addAll(clientRepository.findAll());
        inMemoryDatabase.getEmployees().addAll(employeeRepository.findAll());
        inMemoryDatabase.getProjects().addAll(projectRepository.findAll());
        System.out.println("zaladowane projekty " + inMemoryDatabase.getProjects().size());
        inMemoryDatabase.getTeams().addAll(teamRepository.findAll());
        inMemoryDatabase.getEquipment().addAll(equipmentRepository.findAll());
        inMemoryDatabase.getOrders().addAll(orderRepository.findAll());
        inMemoryDatabase.getInvoices().addAll(invoiceRepository.findAll());
        inMemoryDatabase.getProjectEquipments().addAll(projectEquipmentRepository.findAll());
        
    }

   private void initializeEntities() {
    inMemoryDatabase.getProjects().forEach(Hibernate::initialize);
    inMemoryDatabase.getEquipment().forEach(Hibernate::initialize);
    inMemoryDatabase.getTeams().forEach(Hibernate::initialize);
    inMemoryDatabase.getEmployees().forEach(Hibernate::initialize);
    inMemoryDatabase.getOrders().forEach(Hibernate::initialize);
    inMemoryDatabase.getInvoices().forEach(Hibernate::initialize);
    inMemoryDatabase.getProjectEquipments().forEach(Hibernate::initialize);
}
private void initializeDependencies() {
    inMemoryDatabase.getClients().forEach(client -> {
        Hibernate.initialize(client.getOrders());
        Hibernate.initialize(client.getInvoices());
    });

    inMemoryDatabase.getProjectEquipments().forEach(projectEquipment -> {
        Hibernate.initialize(projectEquipment.getProject());
        Hibernate.initialize(projectEquipment.getEquipment());
    });

    inMemoryDatabase.getProjects().forEach(project -> {
        Hibernate.initialize(project.getTeam());
        Hibernate.initialize(project.getOrder());
        Hibernate.initialize(project.getProjectEquipments());
    });

    inMemoryDatabase.getTeams().forEach(team -> {
        Hibernate.initialize(team.getEmployees());
        Hibernate.initialize(team.getProjects());
        Hibernate.initialize(team.getManager());
    });

    inMemoryDatabase.getEmployees().forEach(employee -> {
        Hibernate.initialize(employee.getManagedTeams());
        Hibernate.initialize(employee.getTeam());
    });

    inMemoryDatabase.getEquipment().forEach(equipment -> {
        Hibernate.initialize(equipment.getProjectEquipments());
    });

    inMemoryDatabase.getOrders().forEach(order -> {
        Hibernate.initialize(order.getClient());
        Hibernate.initialize(order.getProject());
    });

    inMemoryDatabase.getInvoices().forEach(invoice -> {
        Hibernate.initialize(invoice.getClient());
        Hibernate.initialize(invoice.getProject());
    });
}

@Transactional
public void initializeData() {
    System.out.println("startujemy " + inMemoryDatabase.getProjects().size());
    loadDataFromDatabase();
    System.out.println("dane ząładowane " + inMemoryDatabase.getProjects().size());
    initializeEntities();
    System.out.println("encje zainicjalizowane " + inMemoryDatabase.getProjects().size());
    initializeDependencies();
    System.out.println("zależności też " + inMemoryDatabase.getProjects().size());
    detachEntities();
    System.out.println("lądujemy " + inMemoryDatabase.getProjects().size());
    entityManager.clear();
    System.out.println("czyścimy " + inMemoryDatabase.getProjects().size());
   //disableRepositories();
    //System.out.println("wyłączamy " + inMemoryDatabase.getProjects().size());
}

    private void detachEntities() {
        inMemoryDatabase.getClients().forEach(entityManager::detach);
        inMemoryDatabase.getEmployees().forEach(entityManager::detach);
        inMemoryDatabase.getProjects().forEach(entityManager::detach);
        inMemoryDatabase.getTeams().forEach(entityManager::detach);
        inMemoryDatabase.getEquipment().forEach(entityManager::detach);
        inMemoryDatabase.getOrders().forEach(entityManager::detach);
        inMemoryDatabase.getInvoices().forEach(entityManager::detach);
        inMemoryDatabase.getProjectEquipments().forEach(entityManager::detach);
    }

    private void disableRepositories() {
       
        clientRepository = null;
        employeeRepository = null;
        projectRepository = null;
        teamRepository = null;
        equipmentRepository = null;
        orderRepository = null;
        invoiceRepository = null;
        projectEquipmentRepository = null;
    }

    @Transactional
    public void saveDataToDatabase() {
        System.out.println("Rozpoczęcie zapisu danych do bazy...");

        clientRepository.saveAll(inMemoryDatabase.getClients());
        employeeRepository.saveAll(inMemoryDatabase.getEmployees());
        projectRepository.saveAll(inMemoryDatabase.getProjects());
        teamRepository.saveAll(inMemoryDatabase.getTeams());
        equipmentRepository.saveAll(inMemoryDatabase.getEquipment());
        orderRepository.saveAll(inMemoryDatabase.getOrders());
        invoiceRepository.saveAll(inMemoryDatabase.getInvoices());
        projectEquipmentRepository.saveAll(inMemoryDatabase.getProjectEquipments());

        entityManager.flush(); 

        System.out.println("Dane zostały zapisane do bazy danych.");
    }

    @PreDestroy
    public void saveData() {
        databaseService.saveData(inMemoryDatabase);
    }

   

    // Client 
    public List<Client> getClients() {
        return inMemoryDatabase.getClients();
    }

    public void addClient(Client client) {
        inMemoryDatabase.getClients().add(client);
    }

    public void updateClient(Client client) {
        for (int i = 0; i < inMemoryDatabase.getClients().size(); i++) {
            if (inMemoryDatabase.getClients().get(i).getId().equals(client.getId())) {
                inMemoryDatabase.getClients().set(i, client);
                return;
            }
        }
    }

    public boolean deleteClient(Long id) {
        return inMemoryDatabase.getClients().removeIf(c -> c.getId().equals(id));
    }

    // Employee
    public List<Employee> getEmployees() {
        return inMemoryDatabase.getEmployees();
    }

    public void addEmployee(Employee employee) {
        inMemoryDatabase.getEmployees().add(employee);
    }

    public void updateEmployee(Employee employee) {
        for (int i = 0; i < inMemoryDatabase.getEmployees().size(); i++) {
            if (inMemoryDatabase.getEmployees().get(i).getId().equals(employee.getId())) {
                inMemoryDatabase.getEmployees().set(i, employee);
                return;
            }
        }
    }

    public boolean deleteEmployee(Long id) {
        return inMemoryDatabase.getEmployees().removeIf(e -> e.getId().equals(id));
    }

    // Project 
    public List<Project> getProjects() {
        return inMemoryDatabase.getProjects();
    }

    public void addProject(Project project) {
        inMemoryDatabase.getProjects().add(project);
    }

    public void updateProject(Project project) {
        for (int i = 0; i < inMemoryDatabase.getProjects().size(); i++) {
            if (inMemoryDatabase.getProjects().get(i).getId().equals(project.getId())) {
                inMemoryDatabase.getProjects().set(i, project);
                return;
            }
        }
    }

    public boolean deleteProject(Long id) {
        return inMemoryDatabase.getProjects().removeIf(p -> p.getId().equals(id));
    }
    public void saveProject(Project project) {
        updateProject(project);
    }
    public Project getProjectById(Long id) {
        return inMemoryDatabase.getProjects().stream()
            .filter(project -> project.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public boolean addEquipmentToProject(Long projectId, Long equipmentId) {
        Project project = getProjectById(projectId);
        Equipment equipment = getEquipmentById(equipmentId);

        if (project != null && equipment != null) {
            ProjectEquipment projectEquipment = new ProjectEquipment();
            projectEquipment.setProject(project);
            projectEquipment.setEquipment(equipment);
            projectEquipment.setQuantity(1);

            project.getProjectEquipments().add(projectEquipment);
            equipment.getProjectEquipments().add(projectEquipment);

             updateProject(project);
            updateEquipment(equipment);

            return true;
        }
        return false;
    }

    // Team
    public List<Team> getTeams() {
        return inMemoryDatabase.getTeams();
    }

    public void addTeam(Team team) {
        inMemoryDatabase.getTeams().add(team);
    }

    public void updateTeam(Team team) {
        for (int i = 0; i < inMemoryDatabase.getTeams().size(); i++) {
            if (inMemoryDatabase.getTeams().get(i).getId().equals(team.getId())) {
                inMemoryDatabase.getTeams().set(i, team);
                return;
            }
        }
    }

    public boolean deleteTeam(Long id) {
        return inMemoryDatabase.getTeams().removeIf(t -> t.getId().equals(id));
    }

    // Equipment
    public List<Equipment> getEquipment() {
        return inMemoryDatabase.getEquipment();
    }

    public void addEquipment(Equipment equipment) {
        inMemoryDatabase.getEquipment().add(equipment);
    }

    public void updateEquipment(Equipment equipment) {
        for (int i = 0; i < inMemoryDatabase.getEquipment().size(); i++) {
            if (inMemoryDatabase.getEquipment().get(i).getId().equals(equipment.getId())) {
                inMemoryDatabase.getEquipment().set(i, equipment);
                return;
            }
        }
    }

    public boolean deleteEquipment(Long id) {
        return inMemoryDatabase.getEquipment().removeIf(e -> e.getId().equals(id));
    }


public Equipment getEquipmentById(Long id) {
    return inMemoryDatabase.getEquipment().stream()
        .filter(equipment -> equipment.getId().equals(id))
        .findFirst()
        .orElse(null);
}

    // Order
    public List<Order> getOrders() {
        return inMemoryDatabase.getOrders();
    }

    public void addOrder(Order order) {
        inMemoryDatabase.getOrders().add(order);
    }

    public void updateOrder(Order order) {
        for (int i = 0; i < inMemoryDatabase.getOrders().size(); i++) {
            if (inMemoryDatabase.getOrders().get(i).getId().equals(order.getId())) {
                inMemoryDatabase.getOrders().set(i, order);
                return;
            }
        }
    }

    public boolean deleteOrder(Long id) {
        return inMemoryDatabase.getOrders().removeIf(o -> o.getId().equals(id));
    }

    // Invoice
    public List<Invoice> getInvoices() {
        return inMemoryDatabase.getInvoices();
    }

    public void addInvoice(Invoice invoice) {
        inMemoryDatabase.getInvoices().add(invoice);
    }

    public void updateInvoice(Invoice invoice) {
        for (int i = 0; i < inMemoryDatabase.getInvoices().size(); i++) {
            if (inMemoryDatabase.getInvoices().get(i).getId().equals(invoice.getId())) {
                inMemoryDatabase.getInvoices().set(i, invoice);
                return;
            }
        }
    }

    public boolean deleteInvoice(Long id) {
        return inMemoryDatabase.getInvoices().removeIf(i -> i.getId().equals(id));
    }


       // ProjectEquipment
       public List<ProjectEquipment> getProjectEquipments() {
        return inMemoryDatabase.getProjectEquipments();
    }

    public void addProjectEquipment(ProjectEquipment projectEquipment) {
        inMemoryDatabase.getProjectEquipments().add(projectEquipment);
    }

    public void updateProjectEquipment(ProjectEquipment projectEquipment) {
        for (int i = 0; i < inMemoryDatabase.getProjectEquipments().size(); i++) {
            if (inMemoryDatabase.getProjectEquipments().get(i).getId().equals(projectEquipment.getId())) {
                inMemoryDatabase.getProjectEquipments().set(i, projectEquipment);
                return;
            }
        }
    }

    public boolean deleteProjectEquipment(Long id) {
        return inMemoryDatabase.getProjectEquipments().removeIf(pe -> pe.getId().equals(id));
    }

    
    public void initializeStartData() {
        Employee employee = new Employee();
        employee.setFirstName("Olga");
        employee.setLastName("Manager");
        employee.setSpecialization("Construction Manager");
        employee.setSalary(5000.00);
        employee.setManager(true);
        employee.setWorker(false);
        employee.setCompletedProjects(15);
        employee.setExperienced(true);
        inMemoryDatabase.getEmployees().add(employee);

        Equipment cykliniarka = new Equipment();
        cykliniarka.setName("Cykliniarka");
        cykliniarka.setManufacturer("Producent XYZ");
        inMemoryDatabase.getEquipment().add(cykliniarka);

        Equipment wiertarka = new Equipment();
        wiertarka.setName("Wiertarka");
        wiertarka.setManufacturer("Producent XYZ");
        inMemoryDatabase.getEquipment().add(wiertarka);


        Team team = new Team();
        team.setName("Team A");
        team.setCreationDate("2024-01-01");
        team.setManager(employee);
        inMemoryDatabase.getTeams().add(team);

        Project project = new Project();
        project.setBudget(100000.00);
        project.setAccepted(true);
        project.setStatus("IN_PROGRESS");
        project.setTeam(team);
        project.setName("Krasińskiego");
        inMemoryDatabase.getProjects().add(project);

        Project project2 = new Project();
        project2.setBudget(150000.00);
        project2.setAccepted(true);
        project2.setStatus("IN_PROGRESS");
        project2.setTeam(team);
        project2.setName("Jana Pawła");
        inMemoryDatabase.getProjects().add(project2);
    }
}
