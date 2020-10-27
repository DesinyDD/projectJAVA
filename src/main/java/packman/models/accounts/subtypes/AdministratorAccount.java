package packman.models.accounts.subtypes;

import packman.models.accounts.Account;

import java.time.LocalDateTime;

public class AdministratorAccount extends Account {

    /* Constructor */
    public AdministratorAccount(String username, String password, String firstName, String lastName, String picPath, LocalDateTime lastLogin) {
        super(username, password, firstName, lastName, picPath, lastLogin);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
