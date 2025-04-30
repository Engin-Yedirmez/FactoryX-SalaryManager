import java.io.Serializable;

public class SalariedEmployee extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private double weeklySalary;

    // Constructor
    public SalariedEmployee(String firstName, String lastName, String ssn, double weeklySalary) {
        super(firstName, lastName, ssn);
        this.weeklySalary = weeklySalary;
    }

    // Getter and Setter for weeklySalary
    public double getSalary() {
        return weeklySalary;
    }

    public void setSalary(double salary) {
        this.weeklySalary = salary;
    }

    @Override
    public double earnings() {
        return getSalary();
    }

    @Override
    public String toString() {
        return String.format("SalariedEmployee[firstName=%s, lastName=%s, ssn=%s, weeklySalary=%.2f]",
                getFirstName(), getLastName(), getSsn(), weeklySalary);
    }
}
