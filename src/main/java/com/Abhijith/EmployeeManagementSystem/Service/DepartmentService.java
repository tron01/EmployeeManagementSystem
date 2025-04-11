package com.Abhijith.EmployeeManagementSystem.Service;

import com.Abhijith.EmployeeManagementSystem.Dto.DepartmentDTO;
import com.Abhijith.EmployeeManagementSystem.Model.Department;
import com.Abhijith.EmployeeManagementSystem.Repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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


    public Page<DepartmentDTO> getAll(int page) {
        return departmentRepository.findAll(PageRequest.of(page, 20))
                .map(this::toDTO);
    }

    private DepartmentDTO toDTO(Department dept) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        dto.setCreationDate(dept.getCreationDate());
        if (dept.getDepartmentHead() != null)
            dto.setDepartmentHeadId(dept.getDepartmentHead().getId());
        return dto;
    }




}
