package com.zbyszkobud.service;

import com.zbyszkobud.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private ApplicationFacade applicationFacade;

    public List<Employee> getAllEmployees() {
        return applicationFacade.getEmployees();
    }

    public List<Employee> getAllManagers() {
        return applicationFacade.getEmployees().stream()
                .filter(Employee::isManager)
                .toList();
    }

    public Employee getEmployeeById(Long id) {
        return applicationFacade.getEmployees().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        applicationFacade.addEmployee(employee);
        return employee;
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setHireDate(employeeDetails.getHireDate());
            employee.setSpecialization(employeeDetails.getSpecialization());
            employee.setSalary(employeeDetails.getSalary());
            employee.setManager(employeeDetails.isManager());
            employee.setWorker(employeeDetails.isWorker());
            employee.setCompletedProjects(employeeDetails.getCompletedProjects());
            applicationFacade.updateEmployee(employee);
        }
        return employee;
    }

    public boolean deleteEmployee(Long id) {
        return applicationFacade.deleteEmployee(id);
    }
}
