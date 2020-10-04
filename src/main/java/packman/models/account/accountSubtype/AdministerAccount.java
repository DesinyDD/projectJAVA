package packman.models.account.accountSubtype;

import packman.models.account.Account;
import packman.models.account.details.AccountType;
import packman.models.account.details.Name;
import packman.models.account.details.Picture;

import java.time.LocalDateTime;

public class AdministerAccount extends Account {

    /* Constructor */
    public AdministerAccount(Picture picture, LocalDateTime lastLogin, String username, String password, Name name) {
        super(AccountType.ADMINISTER, picture, lastLogin, username, password, name);
    }
}
