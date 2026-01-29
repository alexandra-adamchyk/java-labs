package com.company.model;

public class Task {
    private int id;
    private String description;
    private Integer employeeId;
    private String employeeName;
    
    public Task() {}
    
    public Task(int id, String description, Integer employeeId) {
        this.id = id;
        this.description = description;
        this.employeeId = employeeId;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }
    
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    
    @Override
    public String toString() {
        return String.format("Task{id=%d, description='%s', employeeId=%d}", id, description, employeeId);
    }
}
