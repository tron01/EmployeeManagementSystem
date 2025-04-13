package com.Abhijith.EmployeeManagementSystem.Repository;

import com.Abhijith.EmployeeManagementSystem.Dto.DepartmentEmployeeCountDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.RoleCountDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.RoleReportingChainDTO;
import com.Abhijith.EmployeeManagementSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Employee Role and count
    @Query("select new com.Abhijith.EmployeeManagementSystem.Dto.RoleCountDTO(e.role,COUNT(e))from Employee e group by e.role")
    List<RoleCountDTO> getEmployeeCountByRole();
    //department Name and employee Count
    @Query("select new com.Abhijith.EmployeeManagementSystem.Dto.DepartmentEmployeeCountDTO(d.name,count(e)) from Department d left join d.employees e group by d.name")
    List<DepartmentEmployeeCountDTO> getEmployeeCountByDepartment();

    @Query("SELECT DISTINCT new com.Abhijith.EmployeeManagementSystem.Dto.RoleReportingChainDTO(e.role, m.role) " +
            "FROM Employee e LEFT JOIN e.reportingManager m WHERE m IS NOT NULL")
    List<RoleReportingChainDTO> findReportingChainsByRole();

}
