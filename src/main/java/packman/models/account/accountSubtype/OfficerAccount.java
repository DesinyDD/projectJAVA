package packman.models.account.accountSubtype;

import javafx.scene.image.Image;
import packman.models.account.Account;
import packman.models.account.details.AccountType;
import packman.models.account.details.Name;
import packman.models.account.details.Picture;

import java.time.LocalDateTime;

public class OfficerAccount extends Account {
    private boolean banned;
    private int bannedLoginCount;

    /* Constructor */
    public OfficerAccount(Picture picture, LocalDateTime lastLogin, String username, String password, Name name, boolean banned, int bannedLoginCount) {
        super(AccountType.OFFICER, picture, lastLogin, username, password, name);
        this.banned = banned;
        this.bannedLoginCount = bannedLoginCount;
    }

    /* Setter */
    public void setBanned(boolean banned) { this.banned = banned; }
    public void setBannedLoginCount(int bannedLoginCount) { this.bannedLoginCount = bannedLoginCount; }

    /* Getter */
    public boolean isBanned() { return banned; }
    public int getBannedLoginCount() { return bannedLoginCount; }

    public void switchBan() {
        if (this.isBanned() == false) {
            this.banned = true;
        } else { this.banned = false; }
    }
}
