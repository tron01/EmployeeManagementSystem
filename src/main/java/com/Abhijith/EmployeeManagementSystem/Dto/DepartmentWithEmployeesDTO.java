package com.Abhijith.EmployeeManagementSystem.Dto;

import java.time.LocalDate;
import java.util.List;

public class DepartmentWithEmployeesDTO {
    private Long id;
    private String name;
    private LocalDate creationDate;
    private List<EmployeeLookupDTO> employees;

    public DepartmentWithEmployeesDTO(Long id, String name, LocalDate creationDate, List<EmployeeLookupDTO> employees) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<EmployeeLookupDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeLookupDTO> employees) {
        this.employees = employees;
    }
}
