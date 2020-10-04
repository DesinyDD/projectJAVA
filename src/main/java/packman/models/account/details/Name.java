package packman.models.account.details;

public class Name {
    private String firstName;
    private String lastName;

    /* Constructor */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /* Setter */
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    /* Getter */
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    @Override
    public String toString() { return firstName + "," + lastName; }
}
