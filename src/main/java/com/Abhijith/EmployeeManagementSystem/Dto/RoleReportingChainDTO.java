package com.Abhijith.EmployeeManagementSystem.Dto;

public class RoleReportingChainDTO {
    private String role;
    private String reportsTo;

    public RoleReportingChainDTO(String role, String reportsTo) {
        this.role = role;
        this.reportsTo = reportsTo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }
}
