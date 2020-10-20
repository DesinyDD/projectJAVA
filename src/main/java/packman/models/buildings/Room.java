package packman.models.buildings;

import packman.models.buildings.subtypes.DuplexRoom;
import packman.models.buildings.subtypes.StudioRoom;
import packman.models.buildings.subtypes.SuiteRoom;

public abstract class Room {
    private String towerID;
    private String floorNumber;
    private String roomNumber;

    /* Constructor */
    public Room(String towerID, String floorNumber, String roomNumber) {
        this.towerID     = towerID;
        this.floorNumber = floorNumber;
        this.roomNumber  = roomNumber;
    }

    /* Setter */
    public void setTowerID(String towerID) { this.towerID = towerID; }
    public void setFloorNumber(String floorNumber) { this.floorNumber = floorNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    /* Getter */
    public String getTowerID() { return towerID; }
    public String getFloorNumber() { return floorNumber; }
    public String getRoomNumber() { return roomNumber; }

    public String getRoomID() { return towerID + floorNumber + roomNumber; }

    public abstract int getMaxRoomer();

    public boolean isDuplex() { return this instanceof DuplexRoom; }
    public boolean isStudio() { return this instanceof StudioRoom; }
    public boolean isSuite() { return this instanceof SuiteRoom; }
}
