import java.io.Serializable;

public class BasePlusCommissionEmployee extends CommissionEmployee implements Serializable {
    private static final long serialVersionUID = 1L;
    private double baseSalary;

    // Constructor
    public BasePlusCommissionEmployee(String firstName, String lastName, String ssn, double grossSales, double commissionRate, double baseSalary) {
        super(firstName, lastName, ssn, grossSales, commissionRate);
        this.baseSalary = baseSalary;
    }

    // Getter and Setter for baseSalary
    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public double earnings() {
        return getBaseSalary() + super.earnings(); // Base salary + commission earnings
    }

    @Override
    public String toString() {
        return String.format("BasePlusCommissionEmployee[firstName=%s, lastName=%s, ssn=%s, grossSales=%.2f, commissionRate=%.2f, baseSalary=%.2f]",
                getFirstName(), getLastName(), getSsn(), getGrossSales(), getCommissionRate(), baseSalary);
    }
}
