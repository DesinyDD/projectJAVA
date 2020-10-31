package packman.models.buildings;

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

    public Room getRoom(String roomID) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID)) { return room; }
        }
        throw new RuntimeException("Room with room ID " + roomID + " not found");
    }

    public boolean isAvailable(String roomID) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID)) { return true; }
        }
        return false;
    }

    public ArrayList<Room> toArrayList() { return (ArrayList<Room>) rooms; }
}
