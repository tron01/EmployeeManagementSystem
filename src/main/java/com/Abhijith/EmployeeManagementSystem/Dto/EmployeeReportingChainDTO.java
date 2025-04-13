package com.Abhijith.EmployeeManagementSystem.Dto;

import java.util.List;

public class EmployeeReportingChainDTO {
    private Long employeeId;
    private String employeeName;
    private List<String> reportingChain;

    public EmployeeReportingChainDTO(Long employeeId, String employeeName, List<String> reportingChain) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.reportingChain = reportingChain;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<String> getReportingChain() {
        return reportingChain;
    }

    public void setReportingChain(List<String> reportingChain) {
        this.reportingChain = reportingChain;
    }
}

