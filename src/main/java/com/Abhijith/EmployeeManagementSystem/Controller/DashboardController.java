package com.Abhijith.EmployeeManagementSystem.Controller;

import com.Abhijith.EmployeeManagementSystem.Dto.DashboardDTO;
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

    @GetMapping
    public DashboardDTO getDashboardData() {
        return service.getDashboard();
    }

    @GetMapping("/role_chart")
    public List<RoleCountDTO> getRoleCounts() {
        return service.getRoleCounts();
    }
}

