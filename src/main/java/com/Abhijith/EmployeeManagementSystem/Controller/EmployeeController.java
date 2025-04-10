package com.Abhijith.EmployeeManagementSystem.Controller;


import com.Abhijith.EmployeeManagementSystem.Dto.ChangeDepartmentDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.EmployeeDTO;
import com.Abhijith.EmployeeManagementSystem.Dto.EmployeeLookupDTO;
import com.Abhijith.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService  employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Add new emp
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO dto) {
        try {
            EmployeeDTO created = employeeService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(Map.of("error", e.getReason()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }
    //list of All Emp
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) Boolean lookup) {
        Map<String, Object> response = new HashMap<>();
        if (Boolean.TRUE.equals(lookup)) {
            Page<EmployeeLookupDTO> lookupPage = employeeService.getEmployeeLookups(page);
            response.put("employees", lookupPage.getContent());
            response.put("currentPage", lookupPage.getNumber());
            response.put("totalPages", lookupPage.getTotalPages());
            return ResponseEntity.ok(response);
        }
        Page<EmployeeDTO> employeePage = employeeService.getAllEmployeesPaginated(page);
        response.put("employees", employeePage.getContent());
        response.put("currentPage", employeePage.getNumber());
        response.put("totalPages", employeePage.getTotalPages());
        return ResponseEntity.ok(response);
    }
    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDTO dto = employeeService.getById(id);
            return ResponseEntity.ok(dto);
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(Map.of("error", e.getReason()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }

    //Delete By id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Employee deleted successfully"));
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(Map.of("error", e.getReason()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }


    //Update Basic Info
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        try {
            EmployeeDTO updated = employeeService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(Map.of("error", e.getReason()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }

    //Change Department
    @PutMapping("/{id}/department")
    public ResponseEntity<?> changeDepartment(@PathVariable("id") Long employeeId,@RequestBody ChangeDepartmentDTO request) {
        try {
            EmployeeDTO updated = employeeService.changeDepartment(employeeId, request.getDepartmentId());
            return ResponseEntity.ok(updated);
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(Map.of("error", e.getReason()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }

}
