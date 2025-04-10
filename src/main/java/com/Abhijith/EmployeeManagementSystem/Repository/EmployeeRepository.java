package com.Abhijith.EmployeeManagementSystem.Repository;

import com.Abhijith.EmployeeManagementSystem.Model.Department;
import com.Abhijith.EmployeeManagementSystem.Model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long id);
    
}
