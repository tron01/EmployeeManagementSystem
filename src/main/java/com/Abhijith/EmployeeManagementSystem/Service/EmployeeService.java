package com.Abhijith.EmployeeManagementSystem.Service;

import com.Abhijith.EmployeeManagementSystem.Model.Department;
import com.Abhijith.EmployeeManagementSystem.Model.Employee;
import com.Abhijith.EmployeeManagementSystem.Repository.DepartmentRepository;
import com.Abhijith.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee changeDepartment(Employee employee, Long departmentId) {
        Employee employee1 = employeeRepository.findById(employee.getId()).orElseThrow();
        Department department = departmentRepository.findById(departmentId).orElseThrow();
        employee1.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Page<Employee> getAll(int page) {
        return employeeRepository.findAll(PageRequest.of(page, 20));
    }


}


