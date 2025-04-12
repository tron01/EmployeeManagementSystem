package com.Abhijith.EmployeeManagementSystem.Service;

import com.Abhijith.EmployeeManagementSystem.Dto.EmployeeDTO;
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
    //Add new Employee
    public EmployeeDTO create(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setAddress(dto.getAddress());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setJoinDate(dto.getJoinDate());
        employee.setSalary(dto.getSalary());
        employee.setYearlyBonusPercentage(dto.getYearlyBonusPercentage());
        employee.setRole(dto.getRole());

        // Set Department if provided
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id " + dto.getDepartmentId()));
            employee.setDepartment(department);
        }

        // Set Reporting Manager if provided
        if (dto.getReportingManagerId() != null) {
            Employee manager = employeeRepository.findById(dto.getReportingManagerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporting manager not found with id " + dto.getReportingManagerId()));
            employee.setReportingManager(manager);
        }

        Employee saved = employeeRepository.save(employee);
        return this.toDTO(saved);
    }

    //Get Employee by Id
    public EmployeeDTO getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id " + id)
                );
        return this.toDTO(employee);
    }
    //Delete by Id
    public void deleteById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id " + id)
                );
        employeeRepository.delete(employee);
    }

    //Update Employee Basic info.
    public EmployeeDTO update(Long id,EmployeeDTO dto) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id " + id));
        //  field updates(name,address,role,dob,joinDate,Salary,Bonus%)
        if (dto.getName() != null) existingEmployee.setName(dto.getName());
        if (dto.getAddress() != null) existingEmployee.setAddress(dto.getAddress());
        if (dto.getRole() != null) existingEmployee.setRole(dto.getRole());
        if (dto.getDateOfBirth() != null) existingEmployee.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getJoinDate() != null) existingEmployee.setJoinDate(dto.getJoinDate());
        if (dto.getSalary() != null) existingEmployee.setSalary(dto.getSalary());
        if (dto.getYearlyBonusPercentage() != null) existingEmployee.setYearlyBonusPercentage(dto.getYearlyBonusPercentage());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return toDTO(updatedEmployee);
    }

    //Change Employee Department
    public EmployeeDTO changeDepartment(Long employeeID,Long departmentId) {
       Employee employee = employeeRepository.findById(employeeID).
               orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id " + employeeID));
       Department department = departmentRepository.findById(departmentId).
               orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Department  not found with id " + departmentId));
       //  field updates (departmentId)
       employee.setDepartment(department);
       Employee updatedEmployee = employeeRepository.save(employee);
        return toDTO(updatedEmployee);
    }

    //Employee List with id:name
    public Page<EmployeeLookupDTO> getEmployeeLookups(int page) {
        Page<Employee> employeePage = employeeRepository.findAll(PageRequest.of(page, 20));
        if (page >= employeePage.getTotalPages() && employeePage.getTotalPages() > 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Page not found");
        }
        return employeePage.map(emp -> {
            EmployeeLookupDTO dto = new EmployeeLookupDTO();
            dto.setId(emp.getId());
            dto.setName(emp.getName());
            return dto;
        });
    }

    //get Employee List
    public Page<EmployeeDTO> getAllEmployeesPaginated(int page) {
        Page<Employee> employeesPage = employeeRepository.findAll(PageRequest.of(page, 20));
        if (page >= employeesPage.getTotalPages() && employeesPage.getTotalPages() > 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Page not found");
        }
        return employeesPage.map(employee -> this.toDTO(employee));  // map each Employee to DTO
    }

    // Employee DTO mapper
    public EmployeeDTO toDTO(Employee employee) {
        if (employee == null) return null;
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());
        dto.setAddress(employee.getAddress());
        dto.setRole(employee.getRole());
        dto.setJoinDate(employee.getJoinDate());
        dto.setDateOfBirth(employee.getDateOfBirth());
        dto.setYearlyBonusPercentage(employee.getYearlyBonusPercentage());

        if (employee.getDepartment() != null)
            dto.setDepartmentId(employee.getDepartment().getId());

        if (employee.getReportingManager() != null)
            dto.setReportingManagerId(employee.getReportingManager().getId());

        return dto;
    }
}


