package com.company.model;

public class Department {
    private int id;
    private String name;
    private String phone;
    
    public Department() {}
    
    public Department(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    @Override
    public String toString() {
        return String.format("Department{id=%d, name='%s', phone='%s'}", id, name, phone);
    }
}
