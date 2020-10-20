package packman.models.buildings.subtypes;

import packman.models.buildings.Room;

public class StudioRoom extends Room {

    /* Constructor */
    public StudioRoom(String towerID, String floorNumber, String roomNumber) {
        super(towerID, floorNumber, roomNumber);
    }

    @Override
    public int getMaxRoomer() { return 1; }
}
