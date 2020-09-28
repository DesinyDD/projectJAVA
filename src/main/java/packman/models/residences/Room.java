package packman.models.residences;

import packman.models.people.Resident;

import java.util.ArrayList;

public class Room {
    private String roomNumber;
    private ArrayList<Resident> roomers;
    private int maximumRoomer;

    /* Constructor */
    public Room(String roomNumber, int maximumRoomer) {
        this.roomNumber    = roomNumber;
        this.maximumRoomer = maximumRoomer;
        this.roomers       = new ArrayList<Resident>();
    }

    /* Setter */
    public boolean setMaximumRoomer(int maximumRoomer) {
        if (maximumRoomer > 0) {
            this.maximumRoomer = maximumRoomer;
            return true;
        }
        return false;
    }

    /* Getter */
    public ArrayList<Resident> getRoomers() { return roomers; }
    public int getMaximumRoomer() { return maximumRoomer; }

    public boolean addResident(Resident newRoomer) {
        if (roomers.size() <= maximumRoomer) {
            return roomers.add(newRoomer);
        }
        return false;
    }

    public boolean removeResident(Resident roomer) {
        return roomers.remove(roomer);
    }
}

