package packman.models.people;

import packman.models.people.information.Account;
import packman.models.people.information.Name;

/* Account ของนิติบุคคล(เจ้าหน้าที่ส่วนกลาง) */
public class Officer extends Person {
    private boolean banned;
    private int bannedLoginCount;

    /* Constructor */
    public Officer(Account account, Name name) {
        super(account, name);
        banned = false;
    }

    /* Setter */
    public void setBanned(boolean banned) { this.banned = banned; }
    public void setBannedLoginCount(int bannedLoginCount) { this.bannedLoginCount = bannedLoginCount; }

    /* Getter */
    public boolean isBanned() { return banned; }
    public int getBannedLoginCount() { return bannedLoginCount; }

    public boolean ban() {
        if (this.banned == false) {
            this.banned = true;
            return true;
        }
        return false;
    }

    public boolean unban() {
        if (this.banned == true) {
            this.banned = false;
            this.bannedLoginCount = 0;
            return true;
        }
        return false;
    }

    @Override
    public String getUserType() { return "Officer"; }
}
