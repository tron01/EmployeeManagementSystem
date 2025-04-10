package com.Abhijith.EmployeeManagementSystem.Repository;

import com.Abhijith.EmployeeManagementSystem.Model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
