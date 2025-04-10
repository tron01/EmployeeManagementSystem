package com.Abhijith.EmployeeManagementSystem.Repository;

import com.Abhijith.EmployeeManagementSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long id);
}
