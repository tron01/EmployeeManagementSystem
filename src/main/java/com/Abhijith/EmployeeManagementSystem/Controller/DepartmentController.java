package com.Abhijith.EmployeeManagementSystem.Controller;

import com.Abhijith.EmployeeManagementSystem.Dto.DepartmentDTO;
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

    // POST /department
    @PostMapping
    public ResponseEntity<?> create(@RequestBody DepartmentDTO dto) {
        try {
            DepartmentDTO created = departmentService.create(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", e.getReason()));
        }
    }

    // PUT /department/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DepartmentDTO dto) {
        try {
            DepartmentDTO updated = departmentService.update(dto, id);
            return ResponseEntity.ok(updated);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", e.getReason()));
        }
    }

    // GET /department or /department?page={page}
    @GetMapping
    public ResponseEntity<?> getAllDepartments(@RequestParam(defaultValue = "0") int page) {
       try {
           Page<DepartmentDTO> departmentPage = departmentService.getAll(page);
           Map<String, Object> response = new HashMap<>();
           response.put("departments", departmentPage.getContent());
           response.put("currentPage", departmentPage.getNumber());
           response.put("totalPages", departmentPage.getTotalPages());
           return ResponseEntity.ok(response);
       }catch (ResponseStatusException e) {
           return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", e.getReason()));
       }
    }

    // GET /department/{id}  or /department/{id}?expand=employee
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id, @RequestParam(name = "expand", required = false) String expandParam) {
        try {
            boolean expandEmployees = "employee".equalsIgnoreCase(expandParam);
            if(expandEmployees) {
                DepartmentDTO dto = departmentService.getDepartmentWithEmployee(id, expandEmployees);
                return ResponseEntity.ok(dto);
            }
            DepartmentDTO dto = departmentService.getDepartmentWithEmployee(id,expandEmployees);
            return ResponseEntity.ok(dto);
        }catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", e.getReason()));
        }
    }
    
    // DELETE /department/{id}
    @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Department deleted successfully."));
        }catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", e.getReason()));
        }
    }
}

