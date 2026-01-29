package com.company.dao;

import com.company.DBConnector;
import com.company.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.id, e.last_name, e.first_name, e.position, e.department_id, d.name as department_name " +
                     "FROM Employees e LEFT JOIN Departments d ON e.department_id = d.id ORDER BY e.id";
        
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setLastName(rs.getString("last_name"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setPosition(rs.getString("position"));
                employee.setDepartmentId(rs.getInt("department_id"));
                if (rs.wasNull()) {
                    employee.setDepartmentId(null);
                }
                employee.setDepartmentName(rs.getString("department_name"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all employees: " + e.getMessage());
        }
        
        return employees;
    }
    
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.id, e.last_name, e.first_name, e.position, e.department_id, d.name as department_name " +
                     "FROM Employees e JOIN Departments d ON e.department_id = d.id " +
                     "WHERE e.department_id = ? ORDER BY e.id";
        
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, departmentId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setPosition(rs.getString("position"));
                    employee.setDepartmentId(rs.getInt("department_id"));
                    employee.setDepartmentName(rs.getString("department_name"));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting employees by department: " + e.getMessage());
        }
        
        return employees;
    }
    
    public boolean deleteEmployee(int employeeId) {
        String sql = "DELETE FROM Employees WHERE id = ?";
        
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, employeeId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
}
