package packman.models.accounts.subtypes;

import packman.models.accounts.Account;

import java.time.LocalDateTime;

public class OfficerAccount extends Account {
    private boolean banned;
    private int bannedLoginCount;

    /* Constructor */
    public OfficerAccount(String username, String password, String firstName, String lastName, String picPath, LocalDateTime lastLogin, boolean banned, int bannedLoginCount) {
        super(username, password, firstName, lastName, picPath, lastLogin);
        this.setBanned(banned);
        this.setBannedLoginCount(bannedLoginCount);
    }

    /* Setter */
    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public void setBannedLoginCount(int bannedLoginCount) {
        this.bannedLoginCount = bannedLoginCount;
    }

    /* Getter */
    public boolean isBanned() {
        return banned;
    }

    public int getBannedLoginCount() {
        return bannedLoginCount;
    }

    public String getBanStatus() {
        if (this.isBanned()) {
            return "BANNED";
        }
        else {
            return "NOT-BAN";
        }
    }

    public void switchBan() {
        this.setBanned(!this.banned);
    }

    public void addBannedLogin() {
        this.bannedLoginCount++;
    }

    @Override
    public boolean isAvailable() {
        return !this.isBanned();
    }
}
