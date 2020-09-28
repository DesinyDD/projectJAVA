package packman.models.people.information;

import java.time.LocalDateTime;

public class Account {
    private String username;
    private String password;
    private LocalDateTime lastLogin;

    /* Constructor */
    public Account(String username, String password) {
        this.username  = username;
        this.password  = password;
        this.lastLogin = LocalDateTime.now();
    }

    /* Setter */
    public void setUsername(String username) { this.username = username; }
    public boolean setPassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }
    public void setLastLogin() { this.lastLogin = LocalDateTime.now(); }

    /* Getter */
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public LocalDateTime getLastLogin() { return lastLogin; }
}
