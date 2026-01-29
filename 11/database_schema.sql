-- Database schema for Employee Management System
-- Create database if not exists
CREATE DATABASE IF NOT EXISTS employee_management;
USE employee_management;

-- Departments table
CREATE TABLE IF NOT EXISTS Departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20)
);

-- Employees table
CREATE TABLE IF NOT EXISTS Employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    position VARCHAR(100),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES Departments(id) ON DELETE SET NULL
);

-- Tasks table
CREATE TABLE IF NOT EXISTS Tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    employee_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employees(id) ON DELETE CASCADE
);

-- Insert sample data
INSERT INTO Departments (name, phone) VALUES 
('IT', '+380441234567'),
('HR', '+380442345678'),
('Finance', '+380443456789');

INSERT INTO Employees (last_name, first_name, position, department_id) VALUES 
('Smith', 'John', 'Java Developer', 1),
('Johnson', 'Sarah', 'HR Manager', 2),
('Williams', 'Mike', 'Accountant', 3),
('Brown', 'Anna', 'Frontend Developer', 1),
('Davis', 'Tom', 'Financial Analyst', 3);

INSERT INTO Tasks (description, employee_id) VALUES 
('Develop new feature for user authentication', 1),
('Conduct performance reviews', 2),
('Prepare quarterly financial report', 3),
('Fix responsive design issues', 4),
('Analyze budget variance', 5),
('Implement database optimization', 1),
('Update employee handbook', 2),
('Create financial dashboard', 3);
