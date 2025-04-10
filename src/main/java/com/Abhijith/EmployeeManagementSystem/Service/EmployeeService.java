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

import java.util.List;
import java.util.stream.Collectors;


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

    public List<EmployeeLookupDTO> getEmployeeLookups() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(emp -> new EmployeeLookupDTO(emp.getId(), emp.getName()))
                .collect(Collectors.toList());
    }

    public Page<EmployeeDTO> getAllEmployeesPaginated(int page) {
        Page<Employee> employeesPage = employeeRepository.findAll(PageRequest.of(page, 10));
        return employeesPage.map(this::toDTO);  // map each Employee to DTO
    }

    public EmployeeDTO toDTO(Employee employee) {
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


