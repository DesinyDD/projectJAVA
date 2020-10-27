package packman.models.buildings;

import packman.models.buildings.subtypes.DuplexRoom;
import packman.models.buildings.subtypes.StudioRoom;
import packman.models.buildings.subtypes.SuiteRoom;

public abstract class Room implements Comparable {
    private char towerID;
    private int floorNumber;
    private String roomNumber;

    /* Constructor */
    public Room(char towerID, int floorNumber, String roomNumber) {
        this.setTowerID(towerID);
        this.setFloorNumber(floorNumber);
        this.setRoomNumber(roomNumber);
    }

    /* Setter */
    public void setTowerID(char towerID) { this.towerID = towerID; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    /* Getter */
    public char getTowerID() { return towerID; }
    public int getFloorNumber() { return floorNumber; }
    public String getRoomNumber() { return roomNumber; }

    public String getRoomID() { return String.format("%c%d%s",towerID, floorNumber, roomNumber); }
    public abstract String getRoomType();

    public abstract int getMaxRoomer();

    public boolean isDuplex() { return this instanceof DuplexRoom; }
    public boolean isStudio() { return this instanceof StudioRoom; }
    public boolean isSuite() { return this instanceof SuiteRoom; }

    public boolean isRoomID(String roomID) { return this.getRoomID().equals(roomID); }

//    public Room

    @Override
    public int compareTo(Object o) {
        Room other = (Room) o;
        int room_1 = Integer.parseInt(this.getFloorNumber() + this.getRoomNumber());
        int room_2 = Integer.parseInt(other.getFloorNumber() + other.getRoomNumber());
        if (this.getTowerID() < other.getTowerID()) {
            return -1;
        } else if (this.getTowerID() > other.getTowerID()) {
            return 1;
        } else {
            if (room_1 < room_2) {
                return -1;
            } else if (room_1 > room_2) {
                return 1;
            }
            return 0;
        }
    }
}
