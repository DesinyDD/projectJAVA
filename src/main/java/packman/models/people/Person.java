package packman.models.people;

import packman.models.people.information.Account;
import packman.models.people.information.Name;

import java.time.LocalDateTime;

public abstract class Person {
    private Account account;
    private Name name;

    /* Constructor */
    public Person(Account account, Name name) {
        this.account   = account;
        this.name      = name;
    }

    /* Setter */
    public void setAccount(Account account) { this.account = account; }
    public void setName(Name name) { this.name = name; }

    /* Getter */
    public Account getAccount() { return account; }
    public Name getName() { return name; }

    public LocalDateTime getLastlogin() { return account.getLastLogin(); }
    public String getUsername() { return account.getUsername(); }
    public String getFirstname() { return name.getFirstname(); }
    public String getLastname() { return name.getLastname(); }

    public abstract String getUserType();
}
