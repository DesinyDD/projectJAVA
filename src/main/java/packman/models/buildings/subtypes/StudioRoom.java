package packman.models.buildings.subtypes;

import packman.models.buildings.Room;

public class StudioRoom extends Room {

    /* Constructor */
    public StudioRoom(char towerID, int floorNumber, String roomNumber) {
        super(towerID, floorNumber, roomNumber);
    }

    @Override
    public String getRoomType() { return "Studio"; }

    @Override
    public int getMaxRoomer() { return 1; }
}
