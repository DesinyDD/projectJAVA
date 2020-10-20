package packman.services.roomDataBase;

import packman.models.buildings.RoomList;
import packman.models.buildings.subtypes.DuplexRoom;
import packman.models.buildings.subtypes.StudioRoom;
import packman.models.buildings.subtypes.SuiteRoom;

public class RoomHardcodeDataSource implements RoomDataSource {

    @Override
    public RoomList getRoomsData() {
        RoomList rooms = new RoomList();
        rooms.add(new StudioRoom("B", "5", "06"));
        rooms.add(new SuiteRoom( "B", "2", "11"));
        rooms.add(new DuplexRoom("A", "7", "09"));
        return rooms;
    }

    @Override
    public void setRoomsData(RoomList rooms) {
        // do nothing
    }
}
