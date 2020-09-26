package packman.models;

public class Resident {
    private String name;
    private int roomNumber;
    private int floor;
    private int buildingNumber;

    Resident(String name, int roomNumber, int floor, int buildingNumber) {
        this.name           = name;
        this.roomNumber     = roomNumber;
        this.floor          = floor;
        this.buildingNumber = buildingNumber;
    }

    public String getName() { return name; }

    public int getRoomNumber() { return roomNumber; }

    public int getFloor() { return floor; }

    public int getBuildingNumber() { return buildingNumber; }
}
