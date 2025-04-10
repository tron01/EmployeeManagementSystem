package com.Abhijith.EmployeeManagementSystem.Service;

import com.Abhijith.EmployeeManagementSystem.Dto.EmployeeLookupDTO;
import com.Abhijith.EmployeeManagementSystem.Model.Department;
import com.Abhijith.EmployeeManagementSystem.Model.Employee;
import com.Abhijith.EmployeeManagementSystem.Repository.DepartmentRepository;
import com.Abhijith.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


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
    public Employee update(Long id,Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id " + id));
        existingEmployee.setName(employee.getName());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setYearlyBonusPercentage(employee.getYearlyBonusPercentage());
        existingEmployee.setRole(employee.getRole());
        existingEmployee.setReportingManager(employee.getReportingManager());
        return employeeRepository.save(existingEmployee);
    }
    public Employee changeDepartment(Long departmentId,Employee employee) {
        Employee employee1 = employeeRepository.findById(employee.getId()).orElseThrow();
        Department department = departmentRepository.findById(departmentId).orElseThrow();
        employee1.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Page<Employee> getAll(int page) {
        return employeeRepository.findAll(PageRequest.of(page, 20));
    }

    public Page<EmployeeLookupDTO> getEmployeeLookups(int page) {
        Page<Employee> employeePage = employeeRepository.findAll(PageRequest.of(page, 20));
        return employeePage.map(emp -> new EmployeeLookupDTO(emp.getId(), emp.getName()));
    }

}


