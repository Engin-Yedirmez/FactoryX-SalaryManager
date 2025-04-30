import java.io.Serializable;

public class CommissionEmployee extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private double grossSales;
    private double commissionRate;

    // Constructor
    public CommissionEmployee(String firstName, String lastName, String ssn, double grossSales, double commissionRate) {
        super(firstName, lastName, ssn);
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    // Getters and Setters for grossSales and commissionRate
    public double getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public double earnings() {
        return getGrossSales() * getCommissionRate();
    }

    @Override
    public String toString() {
        return String.format("CommissionEmployee[firstName=%s, lastName=%s, ssn=%s, grossSales=%.2f, commissionRate=%.2f]",
                getFirstName(), getLastName(), getSsn(), grossSales, commissionRate);
    }
}
