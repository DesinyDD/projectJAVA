package packman.models.buildings.subtypes;

import packman.models.buildings.Room;

public class SuiteRoom extends Room {

    /* Constructor */
    public SuiteRoom(char towerID, int floorNumber, String roomNumber) {
        super(towerID, floorNumber, roomNumber);
    }

    @Override
    public String getRoomType() { return "Suite"; }

    @Override
    public int getMaxRoomer() { return 2; }
}
