package packman.models.buildings.subtypes;

import packman.models.buildings.Room;

public class SuiteRoom extends Room {

    /* Constructor */
    public SuiteRoom(String towerID, String floorNumber, String roomNumber) {
        super(towerID, floorNumber, roomNumber);
    }

    @Override
    public int getMaxRoomer() { return 2; }
}
