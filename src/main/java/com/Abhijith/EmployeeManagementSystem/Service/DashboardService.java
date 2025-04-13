package com.Abhijith.EmployeeManagementSystem.Service;

import com.Abhijith.EmployeeManagementSystem.Dto.DashboardDTO;
import com.Abhijith.EmployeeManagementSystem.Repository.DepartmentRepository;
import com.Abhijith.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public DashboardService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public DashboardDTO getDashboard() {

      long EmployeeCount = employeeRepository.count();
      long DepartmentCount = departmentRepository.count();

      return new DashboardDTO(EmployeeCount,DepartmentCount);
    }

}
