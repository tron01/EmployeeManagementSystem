package com.Abhijith.EmployeeManagementSystem.Repository;

import com.Abhijith.EmployeeManagementSystem.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
