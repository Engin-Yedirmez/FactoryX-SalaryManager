import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class EmployeeManager extends JFrame {
    private JTextField firstNameField, lastNameField, ssnField, salaryField, wageField, hoursField, salesField, rateField, baseField, searchField;
    private JButton addButton, searchButton, updateButton, clearButton, showEmployeesButton;  
    private JComboBox<String> employeeTypeComboBox;
    private JTextArea displayArea;
    private ArrayList<Employee> employees;

    public EmployeeManager() {
        employees = new ArrayList<>();
        loadEmployeesFromFile();

        setTitle("Employee Manager");
        setSize(600, 600);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GroupLayout(panel));
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        // Initialize components
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();

        JLabel ssnLabel = new JLabel("SSN:");
        ssnField = new JTextField();
        ssnField.setEditable(false);

        JLabel salaryLabel = new JLabel("Weekly Salary:");
        salaryField = new JTextField();
        salaryField.setEnabled(false);

        JLabel wageLabel = new JLabel("Hourly Wage:");
        wageField = new JTextField();
        wageField.setEnabled(false);

        JLabel hoursLabel = new JLabel("Hours Worked:");
        hoursField = new JTextField();
        hoursField.setEnabled(false);

        JLabel salesLabel = new JLabel("Gross Sales:");
        salesField = new JTextField();
        salesField.setEnabled(false);

        JLabel rateLabel = new JLabel("Commission Rate:");
        rateField = new JTextField();
        rateField.setEnabled(false);

        JLabel baseLabel = new JLabel("Base Salary:");
        baseField = new JTextField();
        baseField.setEnabled(false);

        JLabel searchLabel = new JLabel("Search/Update SSN:");
        searchField = new JTextField();

        employeeTypeComboBox = new JComboBox<>(new String[]{"Select", "Salaried", "Hourly", "Commission", "BasePlusCommission"});
        employeeTypeComboBox.addActionListener(e -> toggleFields());

        addButton = new JButton("Add");
        addButton.addActionListener(e -> addEmployee());

        searchButton = new JButton("Search by SSN");
        searchButton.addActionListener(e -> searchEmployeeBySSN());

        updateButton = new JButton("Update by SSN");
        updateButton.addActionListener(e -> updateEmployeeBySSN());

        clearButton = new JButton("Clear Fields");
        clearButton.addActionListener(e -> clearFields());

        // New button to show all employees
        showEmployeesButton = new JButton("Show Employees");
        showEmployeesButton.addActionListener(e -> showAllEmployees());

        displayArea = new JTextArea(5, 30);
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Set up layout for the panel
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(firstNameLabel)
                .addComponent(lastNameLabel)
                .addComponent(ssnLabel)
                .addComponent(employeeTypeComboBox)
                .addComponent(salaryLabel)
                .addComponent(wageLabel)
                .addComponent(hoursLabel)
                .addComponent(salesLabel)
                .addComponent(rateLabel)
                .addComponent(baseLabel)
                .addComponent(searchLabel)
                .addComponent(showEmployeesButton)); 
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(firstNameField)
                .addComponent(lastNameField)
                .addComponent(ssnField)
                .addComponent(salaryField)
                .addComponent(wageField)
                .addComponent(hoursField)
                .addComponent(salesField)
                .addComponent(rateField)
                .addComponent(baseField)
                .addComponent(searchField)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addComponent(searchButton)
                        .addComponent(updateButton)
                        .addComponent(clearButton))
                .addComponent(displayArea));

        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(firstNameLabel)
                .addComponent(firstNameField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lastNameLabel)
                .addComponent(lastNameField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(ssnLabel)
                .addComponent(ssnField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(employeeTypeComboBox));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(salaryLabel)
                .addComponent(salaryField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(wageLabel)
                .addComponent(wageField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(hoursLabel)
                .addComponent(hoursField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(salesLabel)
                .addComponent(salesField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(rateLabel)
                .addComponent(rateField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(baseLabel)
                .addComponent(baseField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(searchLabel)
                .addComponent(searchField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(addButton)
                .addComponent(searchButton)
                .addComponent(updateButton)
                .addComponent(clearButton));
        vGroup.addComponent(showEmployeesButton);  
        vGroup.addComponent(displayArea);

        layout.setVerticalGroup(vGroup);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);
    }

    private void showAllEmployees() {
        StringBuilder employeeList = new StringBuilder();
        if (employees.isEmpty()) {
            employeeList.append("No employees available.");
        } else {
            for (Employee employee : employees) {
                employeeList.append(employee.toString()).append("\n\n");
            }
        }
        displayArea.setText(employeeList.toString());
    }

    private void toggleFields() {
        String selectedType = (String) employeeTypeComboBox.getSelectedItem();
        boolean salaried = "Salaried".equals(selectedType);
        boolean hourly = "Hourly".equals(selectedType);
        boolean commission = "Commission".equals(selectedType);
        boolean basePlusCommission = "BasePlusCommission".equals(selectedType);

        salaryField.setEnabled(salaried);
        wageField.setEnabled(hourly);
        hoursField.setEnabled(hourly);
        salesField.setEnabled(commission || basePlusCommission);
        rateField.setEnabled(commission || basePlusCommission);
        baseField.setEnabled(basePlusCommission);
    }

    private void addEmployee() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String ssn = UUID.randomUUID().toString();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both first name and last name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double salary = 0;
            double wage = 0;
            double hours = 0;
            double sales = 0;
            double rate = 0;
            double baseSalary = 0;

            String selectedType = (String) employeeTypeComboBox.getSelectedItem();

            if ("Salaried".equals(selectedType)) {
                if (!salaryField.getText().isEmpty()) {
                    salary = Double.parseDouble(salaryField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter salary for salaried employee.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if ("Hourly".equals(selectedType)) {
                if (!wageField.getText().isEmpty() && !hoursField.getText().isEmpty()) {
                    wage = Double.parseDouble(wageField.getText());
                    hours = Double.parseDouble(hoursField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter both wage and hours for hourly employee.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if ("Commission".equals(selectedType)) {
                if (!salesField.getText().isEmpty() && !rateField.getText().isEmpty()) {
                    sales = Double.parseDouble(salesField.getText());
                    rate = Double.parseDouble(rateField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter both sales and commission rate for commission employee.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if ("BasePlusCommission".equals(selectedType)) {
                if (!salesField.getText().isEmpty() && !rateField.getText().isEmpty() && !baseField.getText().isEmpty()) {
                    sales = Double.parseDouble(salesField.getText());
                    rate = Double.parseDouble(rateField.getText());
                    baseSalary = Double.parseDouble(baseField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter sales, commission rate, and base salary for base plus commission employee.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Employee newEmployee = null;
            if ("Salaried".equals(selectedType)) {
                newEmployee = new SalariedEmployee(firstName, lastName, ssn, salary);
            } else if ("Hourly".equals(selectedType)) {
                newEmployee = new HourlyEmployee(firstName, lastName, ssn, wage, hours);
            } else if ("Commission".equals(selectedType)) {
                newEmployee = new CommissionEmployee(firstName, lastName, ssn, sales, rate);
            } else if ("BasePlusCommission".equals(selectedType)) {
                newEmployee = new BasePlusCommissionEmployee(firstName, lastName, ssn, sales, rate, baseSalary);
            }

            employees.add(newEmployee);
            saveEmployeesToFile();
            displayEmployeeInfo(newEmployee);
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to display the information of an employee
    private void displayEmployeeInfo(Employee employee) {
        displayArea.setText(employee.toString());
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        ssnField.setText("");
        salaryField.setText("");
        wageField.setText("");
        hoursField.setText("");
        salesField.setText("");
        rateField.setText("");
        baseField.setText("");
        searchField.setText("");
        employeeTypeComboBox.setSelectedIndex(0);
    }

    private void searchEmployeeBySSN() {
        String ssn = searchField.getText();
        Employee employee = findEmployeeBySSN(ssn);
        if (employee != null) {
            displayEmployeeInfo(employee);
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found with SSN: " + ssn, "Employee Not Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Employee findEmployeeBySSN(String ssn) {
        for (Employee employee : employees) {
            if (employee.getSsn().equals(ssn)) {
                return employee;
            }
        }
        return null;
    }

    private void updateEmployeeBySSN() {
        String ssn = searchField.getText();
        Employee employee = findEmployeeBySSN(ssn);
        if (employee != null) {
            populateFieldsWithEmployeeData(employee);
            // After populating fields, the user can update the employee information
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found with SSN: " + ssn, "Employee Not Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateEmployee(Employee employee) {
        // Get the updated information from the fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String selectedType = (String) employeeTypeComboBox.getSelectedItem();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both first name and last name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double salary = 0;
            double wage = 0;
            double hours = 0;
            double sales = 0;
            double rate = 0;
            double baseSalary = 0;

            if ("Salaried".equals(selectedType)) {
                if (!salaryField.getText().isEmpty()) {
                    salary = Double.parseDouble(salaryField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter salary for salaried employee.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if ("Hourly".equals(selectedType)) {
                if (!wageField.getText().isEmpty() && !hoursField.getText().isEmpty()) {
                    wage = Double.parseDouble(wageField.getText());
                    hours = Double.parseDouble(hoursField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter both wage and hours for hourly employee.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if ("Commission".equals(selectedType)) {
                if (!salesField.getText().isEmpty() && !rateField.getText().isEmpty()) {
                    sales = Double.parseDouble(salesField.getText());
                    rate = Double.parseDouble(rateField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter both sales and commission rate for commission employee.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if ("BasePlusCommission".equals(selectedType)) {
                if (!salesField.getText().isEmpty() && !rateField.getText().isEmpty() && !baseField.getText().isEmpty()) {
                    sales = Double.parseDouble(salesField.getText());
                    rate = Double.parseDouble(rateField.getText());
                    baseSalary = Double.parseDouble(baseField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter sales, commission rate, and base salary for base plus commission employee.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Update employee
            if (employee instanceof SalariedEmployee) {
                ((SalariedEmployee) employee).setSalary(salary);
            } else if (employee instanceof HourlyEmployee) {
                ((HourlyEmployee) employee).setWage(wage);
                ((HourlyEmployee) employee).setHoursWorked(hours);
            } else if (employee instanceof CommissionEmployee) {
                ((CommissionEmployee) employee).setGrossSales(sales);
                ((CommissionEmployee) employee).setCommissionRate(rate);
            } else if (employee instanceof BasePlusCommissionEmployee) {
                ((BasePlusCommissionEmployee) employee).setGrossSales(sales);
                ((BasePlusCommissionEmployee) employee).setCommissionRate(rate);
                ((BasePlusCommissionEmployee) employee).setBaseSalary(baseSalary);
            }

            employee.setFirstName(firstName);
            employee.setLastName(lastName);

            saveEmployeesToFile();
            displayEmployeeInfo(employee);
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateFieldsWithEmployeeData(Employee employee) {
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        ssnField.setText(employee.getSsn());

        if (employee instanceof SalariedEmployee) {
            salaryField.setText(String.valueOf(((SalariedEmployee) employee).getSalary()));
        } else if (employee instanceof HourlyEmployee) {
            wageField.setText(String.valueOf(((HourlyEmployee) employee).getWage()));
            hoursField.setText(String.valueOf(((HourlyEmployee) employee).getHoursWorked()));
        } else if (employee instanceof CommissionEmployee) {
            salesField.setText(String.valueOf(((CommissionEmployee) employee).getGrossSales()));
            rateField.setText(String.valueOf(((CommissionEmployee) employee).getCommissionRate()));
        } else if (employee instanceof BasePlusCommissionEmployee) {
            salesField.setText(String.valueOf(((BasePlusCommissionEmployee) employee).getGrossSales()));
            rateField.setText(String.valueOf(((BasePlusCommissionEmployee) employee).getCommissionRate()));
            baseField.setText(String.valueOf(((BasePlusCommissionEmployee) employee).getBaseSalary()));
        }
    }

    private void saveEmployeesToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employees.ser"))) {
            out.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeesFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employees.ser"))) {
            employees = (ArrayList<Employee>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeManager frame = new EmployeeManager();
            frame.setVisible(true);
        });
    }
}
