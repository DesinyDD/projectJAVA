package packman.models.accounts.subtypes;

import packman.models.accounts.Account;

import java.time.LocalDateTime;

public class OfficerAccount extends Account {
    private boolean banned;
    private int bannedLoginCount;

    private String banStatus;

    /* Constructor */
    public OfficerAccount(String username, String password, String firstName, String lastName, String picPath, LocalDateTime lastLogin, boolean banned, int bannedLoginCount) {
        super(username, password, firstName, lastName, picPath, lastLogin);
        setBanned(banned);
        this.bannedLoginCount = bannedLoginCount;
    }

    /* Setter */
    public void setBanned(boolean banned) {
        this.banned = banned;
        if (this.banned) {
            this.banStatus = "BANNED";
        } else { this.banStatus = "NOT-BAN"; }
    }
    public void setBannedLoginCount(int bannedLoginCount) { this.bannedLoginCount = bannedLoginCount; }

    /* Getter */
    public boolean isBanned() { return banned; }
    public int getBannedLoginCount() { return bannedLoginCount; }

    public String getBanStatus() { return banStatus; }

    public void switchBan() { this.setBanned(!this.banned); }

    public void addBannedLogin() { this.bannedLoginCount++; }

    @Override
    public boolean isAvailable() { return !this.isBanned(); }
}
