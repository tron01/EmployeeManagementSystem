package com.Abhijith.EmployeeManagementSystem.Controller;

import com.Abhijith.EmployeeManagementSystem.Dto.DashboardDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.DepartmentEmployeeCountDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.RoleCountDTO;
import com.Abhijith.EmployeeManagementSystem.Service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }
    //GET /dashboard/count
    @GetMapping("/count")
    public DashboardDTO getDashboardData() {
        return service.getDashboard();
    }
    //GET /dashboard/role_chart
    @GetMapping("/role_chart")
    public List<RoleCountDTO> getRoleCounts() {
        return service.getRoleCounts();
    }
    //GET /dashboard/department_chart
    @GetMapping("/department_chart")
    public List<DepartmentEmployeeCountDTO> getDepartmentEmployeeCounts() {
        return service.getDepartmentEmployeeCounts();
    }
}

