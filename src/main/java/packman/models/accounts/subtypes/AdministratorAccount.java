package packman.models.accounts.subtypes;

import packman.models.accounts.Account;

import java.time.LocalDateTime;

public class AdministratorAccount extends Account {
    private int loginCount;

    /* Constructor */
    public AdministratorAccount(String username, String password, String firstName, String lastName, String picPath, LocalDateTime lastLogin) {
        super(username, password, firstName, lastName, picPath, lastLogin);
    }

    /* Setter */
    public void setLoginCount(int loginCount) { this.loginCount = loginCount; }

    /* Getter */
    public int getLoginCount() { return loginCount; }

    @Override
    public boolean isAvailable() { return true; }
}
