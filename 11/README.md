# Employee Management System

A Java console application for managing employees and tasks using JDBC with MySQL database.

## Features

- View all employees
- View all tasks
- Find employees by department
- Add tasks for specific employees
- View tasks assigned to employees
- Delete employees (with confirmation)

## Database Schema

### Departments
- `id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `name` (VARCHAR(100), NOT NULL, UNIQUE)
- `phone` (VARCHAR(20))

### Employees
- `id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `last_name` (VARCHAR(50), NOT NULL)
- `first_name` (VARCHAR(50), NOT NULL)
- `position` (VARCHAR(100))
- `department_id` (INT, FOREIGN KEY references Departments.id)

### Tasks
- `id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `description` (TEXT, NOT NULL)
- `employee_id` (INT, FOREIGN KEY references Employees.id)

## Setup Instructions

### 1. Prerequisites
- Java 8 or higher
- MySQL Server 5.7 or higher
- MySQL JDBC Driver

### 2. Database Setup
1. Start your MySQL server
2. Execute the `database_schema.sql` script to create the database and tables:
   ```bash
   mysql -u root -p < database_schema.sql
   ```

### 3. Configure Database Connection
Edit the `db.properties` file with your MySQL credentials:
```properties
db.url=jdbc:mysql://localhost:3306/employee_management
db.user=root
db.password=your_actual_password
db.driver=com.mysql.cj.jdbc.Driver
```

### 4. Add MySQL JDBC Driver
Download the MySQL JDBC driver and add it to your project's classpath:
- Download from: https://dev.mysql.com/downloads/connector/j/
- Add the JAR file to your project's lib directory or build path

### 5. Compile and Run
```bash
# Compile
javac -cp ".:mysql-connector-j-8.0.33.jar" -d out src/com/company/*.java src/com/company/*/*.java src/com/company/*/*/*.java

# Run
java -cp ".:mysql-connector-j-8.0.33.jar:out" com.company.Main
```

For Windows, use semicolons instead of colons:
```bash
# Compile
javac -cp ".;mysql-connector-j-8.0.33.jar" -d out src/com/company/*.java src/com/company/*/*.java src/com/company/*/*/*.java

# Run
java -cp ".;mysql-connector-j-8.0.33.jar;out" com.company.Main
```

## Project Structure

```
├── database_schema.sql          # Database creation script
├── db.properties               # Database connection configuration
├── src/
│   └── com/
│       └── company/
│           ├── Main.java                    # Main application class
│           ├── DBConnector.java             # Database connection utility
│           ├── dao/
│           │   ├── EmployeeDAO.java         # Employee data access object
│           │   └── TaskDAO.java             # Task data access object
│           ├── model/
│           │   ├── Department.java          # Department entity
│           │   ├── Employee.java            # Employee entity
│           │   └── Task.java                # Task entity
│           └── util/
│               └── TableFormatter.java      # Console table formatting utility
└── README.md
```

## Usage

1. Run the application
2. Use the menu to navigate through different options
3. Follow the on-screen prompts to perform operations
4. All data is displayed in formatted tables for better readability

## Security Features

- Uses `PreparedStatement` to prevent SQL injection attacks
- Database credentials are stored in a separate properties file
- Input validation for numeric fields
- Confirmation prompt for destructive operations

## Sample Data

The database schema includes sample data for:
- 3 departments (IT, HR, Finance)
- 5 employees with various positions
- 8 tasks assigned to different employees

This sample data allows you to test all functionality immediately after setup.
