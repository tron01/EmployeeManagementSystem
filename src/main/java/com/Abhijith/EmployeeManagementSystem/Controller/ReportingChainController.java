package com.Abhijith.EmployeeManagementSystem.Controller;

import com.Abhijith.EmployeeManagementSystem.Dto.EmployeeReportingChainDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.RoleReportingChainDTO;
import com.Abhijith.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/reporting-chains")
public class ReportingChainController {

    private final EmployeeService employeeService;

    public ReportingChainController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //GET /reporting-chains/by_role
    @GetMapping("/by_role")
    public ResponseEntity<List<RoleReportingChainDTO>> getRoleReportingChains() {
        return ResponseEntity.ok(employeeService.getRoleReportingChains());
    }
    //GET /reporting-chains/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeReportingChainDTO> getReportingChain(@PathVariable Long id) {
        EmployeeReportingChainDTO dto = employeeService.getReportingChainByEmployeeId(id);
        return ResponseEntity.ok(dto);
    }

}
