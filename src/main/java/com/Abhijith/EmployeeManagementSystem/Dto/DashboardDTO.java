package com.Abhijith.EmployeeManagementSystem.Dto;

public class DashboardDTO {
    private long totalEmployees;
    private long totalDepartments;

    public DashboardDTO(long totalEmployees, long totalDepartments) {
        this.totalEmployees = totalEmployees;
        this.totalDepartments = totalDepartments;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }
}
