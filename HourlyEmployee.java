import java.io.Serializable;

public class HourlyEmployee extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private double wage;
    private double hoursWorked;

    // Constructor
    public HourlyEmployee(String firstName, String lastName, String ssn, double wage, double hoursWorked) {
        super(firstName, lastName, ssn);
        this.wage = wage;
        this.hoursWorked = hoursWorked;
    }

    // Getters and Setters for wage and hoursWorked
    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double earnings() {
        return getWage() * getHoursWorked();
    }

    @Override
    public String toString() {
        return String.format("HourlyEmployee[firstName=%s, lastName=%s, ssn=%s, wage=%.2f, hoursWorked=%.2f]",
                getFirstName(), getLastName(), getSsn(), wage, hoursWorked);
    }
}
