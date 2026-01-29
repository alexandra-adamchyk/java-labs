package com.company.dao;

import com.company.DBConnector;
import com.company.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT t.id, t.description, t.employee_id, " +
                     "CONCAT(e.first_name, ' ', e.last_name) as employee_name " +
                     "FROM Tasks t LEFT JOIN Employees e ON t.employee_id = e.id ORDER BY t.id";
        
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setDescription(rs.getString("description"));
                task.setEmployeeId(rs.getInt("employee_id"));
                if (rs.wasNull()) {
                    task.setEmployeeId(null);
                }
                task.setEmployeeName(rs.getString("employee_name"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all tasks: " + e.getMessage());
        }
        
        return tasks;
    }
    
    public List<Task> getTasksByEmployee(int employeeId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT t.id, t.description, t.employee_id, " +
                     "CONCAT(e.first_name, ' ', e.last_name) as employee_name " +
                     "FROM Tasks t JOIN Employees e ON t.employee_id = e.id " +
                     "WHERE t.employee_id = ? ORDER BY t.id";
        
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, employeeId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setDescription(rs.getString("description"));
                    task.setEmployeeId(rs.getInt("employee_id"));
                    task.setEmployeeName(rs.getString("employee_name"));
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting tasks by employee: " + e.getMessage());
        }
        
        return tasks;
    }
    
    public boolean addTaskForEmployee(String description, int employeeId) {
        String sql = "INSERT INTO Tasks (description, employee_id) VALUES (?, ?)";
        
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, description);
            stmt.setInt(2, employeeId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding task for employee: " + e.getMessage());
            return false;
        }
    }
}
