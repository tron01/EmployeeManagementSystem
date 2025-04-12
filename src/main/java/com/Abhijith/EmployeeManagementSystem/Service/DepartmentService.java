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
    public DepartmentDTO create(DepartmentDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setCreationDate(dto.getCreationDate());

        // Optionally set DepartmentHead from ID
        if (dto.getDepartmentHeadId() != null) {
            Employee head = employeeRepository.findById(dto.getDepartmentHeadId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Department head not found with id " + dto.getDepartmentHeadId()));
            department.setDepartmentHead(head);
        }else{
            department.setDepartmentHead(null);
        }

        Department saved = departmentRepository.save(department);
        return toDTO(saved);
    }
    //update department
    public DepartmentDTO update(DepartmentDTO  dto , Long id) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id " + id));
        existing.setName(dto.getName());
        existing.setCreationDate(dto.getCreationDate());
        // Optionally set DepartmentHead from ID
        if (dto.getDepartmentHeadId() != null) {
            Employee head = employeeRepository.findById(dto.getDepartmentHeadId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department head not found with id " + dto.getDepartmentHeadId()));
            existing.setDepartmentHead(head);
        } else {
            existing.setDepartmentHead(null);
        }

        Department saved = departmentRepository.save(existing);
        return toDTO(saved);
    }
    //delete department
    public void delete(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id " + id));

        if (department.getEmployees() != null && !department.getEmployees().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete department with assigned employees");
        }
        departmentRepository.deleteById(id);
    }
    //get department List
    public Page<DepartmentDTO> getAll(int page) {
        Page<Department> departmentPage = departmentRepository.findAll(PageRequest.of(page, 20));
        // Handle page not found case
        if (page >= departmentPage.getTotalPages() && departmentPage.getTotalPages() > 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Page not found");
        }
        return departmentPage.map(this::toDTO);
    }

    //get departmentInfoById (when expand=true -> shows employee list of that department)
    public DepartmentDTO getDepartmentWithEmployee(Long id, boolean expandEmployees) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id " + id));

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

    //DepartmentDTO mapping (add,update,departmentList)
    private DepartmentDTO toDTO(Department dept) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        dto.setCreationDate(dept.getCreationDate());
        dto.setEmployeeCount(dept.getEmployees().size());
        if (dept.getDepartmentHead() != null)
            dto.setDepartmentHeadId(dept.getDepartmentHead().getId());
        return dto;
    }
    //EmployeeDTO mapping (getDepartmentWithEmployee)
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
        dto.setDepartmentId(employee.getDepartment() != null ? employee.getDepartment().getId() : null);
        dto.setReportingManagerId(employee.getReportingManager() != null ? employee.getReportingManager().getId() : null);
        return dto;
    }


}
