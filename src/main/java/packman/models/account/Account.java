package packman.models.account;

import javafx.scene.image.Image;
import packman.models.account.details.AccountType;
import packman.models.account.details.Name;
import packman.models.account.details.Picture;

import java.time.LocalDateTime;

public abstract class Account {
    private AccountType accountType;
    private Picture picture;
    private LocalDateTime lastLogin;
    private Name name;
    private String username;
    private String password;

    /* Constructor */
    public Account(AccountType accountType, Picture picture, LocalDateTime lastLogin, String username, String password, Name name) {
        this.accountType = accountType;
        this.picture     = picture;
        this.lastLogin   = lastLogin;
        this.username    = username;
        this.password    = password;
        this.name        = name;
    }

    /* Setter */
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
    public void setImage(Picture picture) { this.picture = picture; }
    public void setLastLogin() { this.lastLogin = LocalDateTime.now(); }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setName(Name name) { this.name = name; }

    /* Getter */
    public AccountType getAccountType() { return accountType; }
    public Picture getPicture() { return picture; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Name getName() { return name; }

    public Image getImage() { return picture.getImage(); }
    public String getImagePath() { return picture.getPath(); }
    public String getFirstName() { return name.getFirstName(); }
    public String getLastName() { return name.getLastName(); }
}
