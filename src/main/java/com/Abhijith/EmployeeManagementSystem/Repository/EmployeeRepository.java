package com.Abhijith.EmployeeManagementSystem.Repository;

import com.Abhijith.EmployeeManagementSystem.Dto.RoleCountDTO;
import com.Abhijith.EmployeeManagementSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //give Employee Role and count
    @Query("select new com.Abhijith.EmployeeManagementSystem.Dto.RoleCountDTO(e.role,COUNT(e))from Employee e group by e.role")
    List<RoleCountDTO> getEmployeeCountByRole();

}
