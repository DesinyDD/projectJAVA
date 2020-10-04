package packman.services.roomDataBase;

import packman.models.building.Room;
import packman.models.building.RoomList;
import packman.models.building.details.RoomType;

public class RoomHardcodeDataSource implements RoomDataSource {

    @Override
    public RoomList getRoomsData() {
        RoomList rooms = new RoomList();
        rooms.add(new Room(RoomType.STUDIO, "123", "7", "A"));
        rooms.add(new Room(RoomType.DUPLEX, "567", "2", "A"));
        return rooms;
    }

    @Override
    public void setRoomsData(RoomList rooms) {
        // do nothing
    }
}
