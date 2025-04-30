# Factory X Employee Salary Management System

## 📌 Overview

This Java application is a GUI-based payroll system developed for **Factory X**, designed to manage and calculate payments for various types of employees. It implements a structured object-oriented approach using inheritance and polymorphism. 

The software allows the user to add, update, search, and view salary information for employees, and all data is persistently stored in a text file.

## ✨ Features

- Add new employees of different types (Salaried, Hourly, Commission, BasePlusCommission)
- Search and update employees by Social Security Number (SSN)
- Save employee data to a file and load it on startup
- GUI developed using Java Swing
- Polymorphic behavior via `Payable` interface
- Uses inheritance to avoid code redundancy
- Validates all input fields

## 🧱 Class Structure

- `Payable` (interface): Declares `getPaymentAmount()`
- `Employee` (abstract): Base class with `firstName`, `lastName`, `SSN`
  - `SalariedEmployee`: Paid a fixed weekly salary
  - `HourlyEmployee`: Paid by the hour + overtime
  - `CommissionEmployee`: Paid a percentage of sales
  - `BasePlusCommissionEmployee`: Receives base salary + commission
- `EmployeeManager` (GUI): JFrame-based interface for interacting with employees
- File-based storage: Reads/writes employee data to a `.txt` file

## 🖥 How to Run

1. Clone the repository or download the ZIP.
2. Open the project in **Eclipse IDE** or any Java IDE of your choice.
3. Run the `EmployeeManager.java` class to launch the GUI.
4. Use dropdowns to select employee type and manage data accordingly.

## 📝 Example Usage

- To add an employee:
  - Choose the type (e.g., HourlyEmployee)
  - Enter first name, last name, wage, and hours
  - Click "Add"
  
- To search:
  - Enter the SSN in the "Search/Update" field
  - Click "Search"

- To update:
  - Perform search by SSN first
  - Change the fields you want
  - Click "Update"

## 🧰 Requirements

- Java JDK 8 or higher
- Eclipse IDE (Recommended)
- Windows OS (Preferred for GUI look and feel)
- No external libraries are required

### 📄 License
This project was developed for educational purposes and is shared as a sample academic assignment.