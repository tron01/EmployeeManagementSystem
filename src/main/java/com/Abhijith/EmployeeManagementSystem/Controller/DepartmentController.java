package com.Abhijith.EmployeeManagementSystem.Controller;

import com.Abhijith.EmployeeManagementSystem.Dto.DepartmentDTO;
import com.Abhijith.EmployeeManagementSystem.Model.Department;
import com.Abhijith.EmployeeManagementSystem.Service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Create new department
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department) {
        try {
            DepartmentDTO created = departmentService.create(department);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }

    // PUT /department/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Department department) {
        try {
            DepartmentDTO updated = departmentService.update(department, id);
            return ResponseEntity.ok(updated);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", e.getReason()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }

    // GET /department?page={page}
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDepartments(@RequestParam(defaultValue = "0") int page) {
        Page<DepartmentDTO> departmentPage = departmentService.getAll(page);
        Map<String, Object> response = new HashMap<>();
        response.put("departments", departmentPage.getContent());
        response.put("currentPage", departmentPage.getNumber());
        response.put("totalPages", departmentPage.getTotalPages());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id, @RequestParam(name = "expand", required = false) String expandParam) {
        boolean expandEmployees = "employee".equalsIgnoreCase(expandParam);
        if(expandEmployees) {
            DepartmentDTO dto = departmentService.getDepartmentWithEmployee(id, expandEmployees);
            return ResponseEntity.ok(dto);
        }
        DepartmentDTO dto = departmentService.getDepartmentWithEmployee(id,expandEmployees);
        return ResponseEntity.ok(dto);

    }


}

