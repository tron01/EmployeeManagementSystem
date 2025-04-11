package com.Abhijith.EmployeeManagementSystem.Service;

import com.Abhijith.EmployeeManagementSystem.Dto.DepartmentDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.EmployeeDTO;
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
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    //add department
    public DepartmentDTO create(Department department) {
        Department saved = departmentRepository.save(department);
        return toDTO(saved);
    }
    //update department
    public DepartmentDTO update(Department department , Long id) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
        existing.setName(department.getName());
        existing.setDepartmentHead(department.getDepartmentHead());
        return toDTO(departmentRepository.save(existing));
    }
    //delete department
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found with id " + id);
        }
        departmentRepository.deleteById(id);
    }
    //get All department
    public Page<DepartmentDTO> getAll(int page) {
        return departmentRepository.findAll(PageRequest.of(page, 20))
                .map(this::toDTO);
    }


    //get department with employee (when extend shows employee list of that department)
    public DepartmentDTO getDepartmentWithEmployee(Long id, boolean expandEmployees) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        dto.setCreationDate(dept.getCreationDate());

        dto.setDepartmentHeadId(
                dept.getDepartmentHead() != null ? dept.getDepartmentHead().getId() : null
        );

        if (expandEmployees) {
            List<EmployeeDTO> employeeDTOs = dept.getEmployees()
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());

            dto.setEmployees(employeeDTOs);
            dto.setEmployeeCount(employeeDTOs.size());
        } else {
            dto.setEmployeeCount(dept.getEmployees().size());
        }
        return dto;
    }


    //DepartmentDTO mapping
    private DepartmentDTO toDTO(Department dept) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        dto.setCreationDate(dept.getCreationDate());
        if (dept.getDepartmentHead() != null)
            dto.setDepartmentHeadId(dept.getDepartmentHead().getId());
        return dto;
    }
    //EmployeeDTO mapping
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());
        dto.setAddress(employee.getAddress());
        dto.setRole(employee.getRole());
        dto.setJoinDate(employee.getJoinDate());
        dto.setDateOfBirth(employee.getDateOfBirth());
        dto.setYearlyBonusPercentage(employee.getYearlyBonusPercentage());

        // Set department ID and reporting manager ID safely
        dto.setDepartmentId(employee.getDepartment() != null ? employee.getDepartment().getId() : null);
        dto.setReportingManagerId(employee.getReportingManager() != null ? employee.getReportingManager().getId() : null);

        return dto;
    }


}
