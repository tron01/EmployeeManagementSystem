package com.Abhijith.EmployeeManagementSystem.Service;

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

    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    public Department update(Department department , Long id) {
        Department department1 = departmentRepository.findById(id).orElseThrow();
        department1.setName(department.getName());
        return departmentRepository.save(department1);
    }

    public Page<Department> getAll(int page) {
        return departmentRepository.findAll(PageRequest.of(page, 20));
    }


}
