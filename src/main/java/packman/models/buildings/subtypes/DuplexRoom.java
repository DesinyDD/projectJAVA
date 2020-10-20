package packman.models.buildings.subtypes;

import packman.models.buildings.Room;

public class DuplexRoom extends Room {

    /* Constructor */
    public DuplexRoom(String towerID, String floorNumber, String roomNumber) {
        super(towerID, floorNumber, roomNumber);
    }

    @Override
    public int getMaxRoomer() { return 4; }
}
