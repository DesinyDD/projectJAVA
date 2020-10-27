package packman.models.buildings.subtypes;

import packman.models.buildings.Room;

public class DuplexRoom extends Room {

    /* Constructor */
    public DuplexRoom(char towerID, int floorNumber, String roomNumber) {
        super(towerID, floorNumber, roomNumber);
    }

    @Override
    public String getRoomType() { return "Duplex"; }

    @Override
    public int getMaxRoomer() { return 4; }
}
