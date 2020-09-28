package packman.models.people;

import packman.models.people.information.Account;
import packman.models.people.information.Name;
import packman.models.residences.Room;

public class Resident extends Person {
    private Room residence;

    /* Constructor */
    public Resident(Account account, Name name, Room residence) {
        super(account, name);
        this.residence = residence;
    }

    /* Setter */
    public void setResidence(Room residence) { this.residence = residence; }

    /* Getter */
    public Room getResidence() { return residence; }

    @Override
    public String getUserType() { return "Resident"; }
}
