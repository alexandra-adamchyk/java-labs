package com.company;

import com.company.dao.EmployeeDAO;
import com.company.dao.TaskDAO;
import com.company.model.Employee;
import com.company.model.Task;
import com.company.util.TableFormatter;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final TaskDAO taskDAO = new TaskDAO();
    
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Management System!");
        
        while (true) {
            TableFormatter.printMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        showAllEmployees();
                        break;
                    case 2:
                        showAllTasks();
                        break;
                    case 3:
                        findEmployeesByDepartment();
                        break;
                    case 4:
                        addTaskForEmployee();
                        break;
                    case 5:
                        showTasksForEmployee();
                        break;
                    case 6:
                        deleteEmployee();
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void showAllEmployees() {
        System.out.println("\n--- All Employees ---");
        List<Employee> employees = employeeDAO.getAllEmployees();
        TableFormatter.printEmployeesTable(employees);
    }
    
    private static void showAllTasks() {
        System.out.println("\n--- All Tasks ---");
        List<Task> tasks = taskDAO.getAllTasks();
        TableFormatter.printTasksTable(tasks);
    }
    
    private static void findEmployeesByDepartment() {
        System.out.print("Enter department ID: ");
        try {
            int departmentId = Integer.parseInt(scanner.nextLine());
            List<Employee> employees = employeeDAO.getEmployeesByDepartment(departmentId);
            System.out.println("\n--- Employees in Department " + departmentId + " ---");
            TableFormatter.printEmployeesTable(employees);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid department ID.");
        }
    }
    
    private static void addTaskForEmployee() {
        System.out.print("Enter employee ID: ");
        try {
            int employeeId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter task description: ");
            String description = scanner.nextLine();
            
            boolean success = taskDAO.addTaskForEmployee(description, employeeId);
            if (success) {
                System.out.println("Task added successfully!");
            } else {
                System.out.println("Failed to add task. Please check if employee exists.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid employee ID.");
        }
    }
    
    private static void showTasksForEmployee() {
        System.out.print("Enter employee ID: ");
        try {
            int employeeId = Integer.parseInt(scanner.nextLine());
            List<Task> tasks = taskDAO.getTasksByEmployee(employeeId);
            System.out.println("\n--- Tasks for Employee " + employeeId + " ---");
            TableFormatter.printTasksTable(tasks);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid employee ID.");
        }
    }
    
    private static void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        try {
            int employeeId = Integer.parseInt(scanner.nextLine());
            System.out.print("Are you sure you want to delete employee " + employeeId + "? (y/N): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                boolean success = employeeDAO.deleteEmployee(employeeId);
                if (success) {
                    System.out.println("Employee deleted successfully!");
                } else {
                    System.out.println("Failed to delete employee. Please check if employee exists.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid employee ID.");
        }
    }
}
