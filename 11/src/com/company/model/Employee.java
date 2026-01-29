package com.company.model;

public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String position;
    private Integer departmentId;
    private String departmentName;
    
    public Employee() {}
    
    public Employee(int id, String lastName, String firstName, String position, Integer departmentId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.departmentId = departmentId;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    
    public Integer getDepartmentId() { return departmentId; }
    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }
    
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    
    @Override
    public String toString() {
        return String.format("Employee{id=%d, lastName='%s', firstName='%s', position='%s', departmentId=%d}", 
                id, lastName, firstName, position, departmentId);
    }
}
