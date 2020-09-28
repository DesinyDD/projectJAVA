package packman.models.people;

import packman.models.people.information.Account;
import packman.models.people.information.Name;

/* Account ของผู้ดูแลระบบ */
public class Administer extends Person {

    /* Constructor */
    public Administer(Account account, Name name) {
        super(account, name);
    }

    @Override
    public String getUserType() { return "Administer"; }
}
