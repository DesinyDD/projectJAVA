package packman.models.accounts;

import packman.models.accounts.subtypes.AdministratorAccount;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.accounts.subtypes.ResidentAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String picPath;
    private LocalDateTime lastLogin;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String lastLoginString;

    /* Constructor */
    public Account(String username, String password, String firstName, String lastName, String picPath, LocalDateTime lastLogin) {
        this.username  = username;
        this.password  = password;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.picPath   = picPath;
        setLastLogin(lastLogin);
    }

    /* Setter */
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPicPath(String picPath) { this.picPath = picPath; }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
        if (!lastLogin.equals(LocalDateTime.of(1, 1, 1, 1, 1, 1))) {
            this.lastLoginString = lastLogin.format(formatter);
        } else { this.lastLoginString = "NewAccount"; }
    }


    /* Getter */
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public String getLastLoginString() { return lastLoginString; }

    public String getPicPath() { return picPath; }
    public LocalDateTime getLastLogin() { return lastLogin; }

    public boolean validation(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean isUsername(String username) { return this.username.equals(username); }
    public boolean isPassword(String password) { return this.password.equals(password); }

    public boolean isResident() { return this instanceof ResidentAccount; }
    public boolean isOfficer() { return this instanceof OfficerAccount; }
    public boolean isAdministrator() { return this instanceof AdministratorAccount; }

    public ResidentAccount toResident() { return (ResidentAccount) this; }
    public OfficerAccount toOfficer() { return (OfficerAccount) this; }
    public AdministratorAccount toAdministrator() { return (AdministratorAccount) this; }

    public abstract boolean isAvailable();
}