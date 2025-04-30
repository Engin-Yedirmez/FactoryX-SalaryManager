import java.io.Serializable;

public abstract class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private final String ssn;  // Make SSN immutable (final)

    // Constructor
    public Employee(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;  // Set SSN in the constructor
    }

    // Getter for SSN (no setter)
    public String getSsn() {
        return ssn;
    }

    // Getter and Setter for firstName and lastName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Abstract method to be implemented by subclasses
    public abstract double earnings();

    @Override
    public String toString() {
        return String.format("Employee[firstName=%s, lastName=%s, ssn=%s]", firstName, lastName, ssn);
    }
}
