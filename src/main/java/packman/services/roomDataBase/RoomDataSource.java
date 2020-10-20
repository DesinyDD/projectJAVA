package packman.services.roomDataBase;

import packman.models.buildings.RoomList;

public interface RoomDataSource {
    RoomList getRoomsData();

    void setRoomsData(RoomList rooms);
}


