package packman.models.accounts;

import packman.models.accounts.subtypes.AdministratorAccount;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.accounts.subtypes.ResidentAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account implements Comparable<Account> {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String picPath;
    private LocalDateTime lastLogin;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /* Constructor */
    public Account(String username, String password, String firstName, String lastName, String picPath, LocalDateTime lastLogin) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPicPath(picPath);
        this.setLastLogin(lastLogin);
    }

    /* Setter */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    /* Getter */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPicPath() {
        return picPath;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getLastLoginString() {
        if (!lastLogin.equals(LocalDateTime.of(1, 1, 1, 1, 1, 1))) {
            return this.lastLogin.format(formatter);
        }
        else {
            return "NewAccount";
        }
    }

    public boolean validation(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean isUsername(String username) {
        return this.username.equals(username);
    }

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }

    public boolean isResident() {
        return this instanceof ResidentAccount;
    }

    public boolean isOfficer() {
        return this instanceof OfficerAccount;
    }

    public boolean isAdministrator() {
        return this instanceof AdministratorAccount;
    }

    public ResidentAccount toResident() {
        return (ResidentAccount) this;
    }

    public OfficerAccount toOfficer() {
        return (OfficerAccount) this;
    }

    public AdministratorAccount toAdministrator() {
        return (AdministratorAccount) this;
    }

    public abstract boolean isAvailable();

    @Override
    public int compareTo(Account other) {
        if (this.lastLogin.isBefore(other.lastLogin)) {
            return 1;
        }
        else if (this.lastLogin.isAfter(other.lastLogin)) {
            return -1;
        }
        return 0;
    }
}