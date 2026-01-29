package com.company.util;

import com.company.model.Employee;
import com.company.model.Task;

import java.util.List;

public class TableFormatter {
    
    public static void printEmployeesTable(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        // Table header
        System.out.println("\n" + "=".repeat(100));
        System.out.printf("%-5s %-15s %-15s %-20s %-15s %-20s%n", 
                "ID", "Last Name", "First Name", "Position", "Dept ID", "Department");
        System.out.println("=".repeat(100));
        
        // Table rows
        for (Employee emp : employees) {
            System.out.printf("%-5d %-15s %-15s %-20s %-15s %-20s%n",
                    emp.getId(),
                    emp.getLastName(),
                    emp.getFirstName(),
                    emp.getPosition() != null ? emp.getPosition() : "N/A",
                    emp.getDepartmentId() != null ? emp.getDepartmentId().toString() : "N/A",
                    emp.getDepartmentName() != null ? emp.getDepartmentName() : "N/A");
        }
        System.out.println("=".repeat(100));
        System.out.println("Total: " + employees.size() + " employees\n");
    }
    
    public static void printTasksTable(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        
        // Table header
        System.out.println("\n" + "=".repeat(120));
        System.out.printf("%-5s %-10s %-80s%n", 
                "ID", "Employee", "Description");
        System.out.println("=".repeat(120));
        
        // Table rows
        for (Task task : tasks) {
            String description = task.getDescription();
            if (description.length() > 80) {
                description = description.substring(0, 77) + "...";
            }
            
            System.out.printf("%-5d %-10s %-80s%n",
                    task.getId(),
                    task.getEmployeeName() != null ? task.getEmployeeName() : "Unassigned",
                    description);
        }
        System.out.println("=".repeat(120));
        System.out.println("Total: " + tasks.size() + " tasks\n");
    }
    
    public static void printMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Show all employees");
        System.out.println("2. Show all tasks");
        System.out.println("3. Find employees by department");
        System.out.println("4. Add task for employee");
        System.out.println("5. Show tasks for employee");
        System.out.println("6. Delete employee");
        System.out.println("0. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");
    }
}
