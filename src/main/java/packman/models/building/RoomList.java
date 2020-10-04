package packman.models.building;

import java.util.ArrayList;

public class RoomList {
    private ArrayList<Room> rooms;

    /* Constructor */
    public RoomList() { this.rooms = new ArrayList<Room>(); }

    /* Setter */
    public void setRooms(ArrayList<Room> rooms) { this.rooms = rooms; }

    /* Getter */
    public ArrayList<Room> getRooms() { return rooms; }

    public boolean add(Room room) { return rooms.add(room); }

    public Room findByRoomNumber(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) { return room; }
        }
        throw new RuntimeException("Room with room number " + roomNumber + " not found");
    }

    public boolean isAvailable(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) { return true; }
        }
        return false;
    }


}
