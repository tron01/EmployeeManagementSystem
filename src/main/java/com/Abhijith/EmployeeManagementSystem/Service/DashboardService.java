package com.Abhijith.EmployeeManagementSystem.Service;

import com.Abhijith.EmployeeManagementSystem.Dto.DashboardDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.DepartmentEmployeeCountDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.RoleCountDTO;
import com.Abhijith.EmployeeManagementSystem.Repository.DepartmentRepository;
import com.Abhijith.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public DashboardService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }
    //get Count of employee,Department
    public DashboardDTO getDashboard() {
      long EmployeeCount = employeeRepository.count();
      long DepartmentCount = departmentRepository.count();
      return new DashboardDTO(EmployeeCount,DepartmentCount);
    }
    //List of Role:count
    public List<RoleCountDTO> getRoleCounts() {
        return employeeRepository.getEmployeeCountByRole();
    }
    //list of Department:count
    public List<DepartmentEmployeeCountDTO> getDepartmentEmployeeCounts() {
        return employeeRepository.getEmployeeCountByDepartment();
    }
}
