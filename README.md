# Computer Programming 1 - Payroll System

This is a console-based payroll system developed for a Computer Programming 1 course. The system reads employee data and attendance records from CSV files, calculates the total hours worked, gross salary, mandatory contributions (PhilHealth, Pag-IBIG, SSS), taxable income, withholding tax, and finally the net salary of an employee for a specified month.

## Features
- Employee data and attendance management
- Automatic calculation of monthly work hours and days
- Computation of gross salary based on hourly rates
- Deduction of mandatory contributions
- Calculation of taxable income and withholding tax
- Display of net salary after deductions

## CSV Files

### 1. `employee_data.csv`
This file contains the details of each employee.

**Columns:**
- **Employee ID**: Unique identifier for each employee (e.g., `0001`).
- **Last Name**: Employee's last name (e.g., `Doe`).
- **First Name**: Employee's first name (e.g., `John`).
- **Birthday**: Employee's date of birth in `yyyy-MM-dd` format (e.g., `1990-01-15`).
- **Basic Salary**: Employee's basic monthly salary (e.g., `30000.00`).
- **Hourly Rate**: Employee's hourly rate (e.g., `150.00`).

**Example:**
```csv
0001,Doe,John,1990-01-15,30000.00,150.00
0002,Smith,Jane,1985-07-22,40000.00,200.00}
```

### 2. `employee_attendance.csv`
This file records the attendance of each employee, including clock-in and clock-out times.

**Columns:**
- **Employee ID**: Unique identifier for each employee (e.g., `E001`).
- **Date**: The date of attendance in `dd/MM/yyyy` format (e.g., `15/08/2024`).
- **Time In**: Time the employee clocked in, in `HH:mm:ss` format (e.g., `08:00:00`).
- **Time Out**: Time the employee clocked out, in `HH:mm:ss` format (e.g., `17:00:00`).

**Example:**
```csv
0001,15/08/2024,08:00:00,17:00:00
0001,16/08/2024,08:00:00,17:00:00
0002,15/08/2024,08:30:00,17:30:00}
```
