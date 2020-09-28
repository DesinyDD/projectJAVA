package packman.models.people.information;

import packman.Utility;

public class Name {
    private String title;
    private String firstname;
    private String lastname;

    /* Constructor */
    public Name(String firstname, String lastname, String title) {
        this.title      = title;
        this.firstname  = firstname;
        this.lastname   = lastname;
    }
    public Name(String firstname, String lastname) {
        this(firstname, lastname, null);
    }

    /* Setter */
    public void setTitle(String title) { this.title = title; }
        /* Capitalized first character & Remove non-digit character by static method */
    public void setFirstname(String firstName) { this.firstname = Utility.capitalizedFirstCharOfString(Utility.removeAllNumeric(firstname)); }
    public void setLastname(String lastName) { this.lastname = Utility.capitalizedFirstCharOfString(Utility.removeAllNumeric(lastname)); }

    /* Getter */
    public String getTitle() { return title; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", title, firstname, lastname);
    }
}
