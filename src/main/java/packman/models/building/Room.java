package packman.models.building;

import packman.models.building.details.RoomType;

public class Room {
    private RoomType roomType;
    private String roomNumber;
    private String floorNumber;
    private String towerNumber;

    /* Constructor */
    public Room(RoomType roomType, String roomNumber, String floorNumber, String towerNumber) {
        this.roomType    = roomType;
        this.roomNumber  = roomNumber;
        this.floorNumber = floorNumber;
        this.towerNumber = "A";
    }

    /* Setter */
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setFloorNumber(String floorNumber) { this.floorNumber = floorNumber; }
    public void setTowerNumber(String towerNumber) { this.towerNumber = towerNumber; }

    /* Getter */
    public RoomType getRoomType() { return roomType; }
    public String getRoomNumber() { return roomNumber; }
    public String getFloorNumber() { return floorNumber; }
    public String getTowerNumber() { return towerNumber; }
}
