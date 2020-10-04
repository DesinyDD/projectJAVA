package packman.services.roomDataBase;

import packman.models.building.RoomList;

public interface RoomDataSource {
    RoomList getRoomsData();

    void setRoomsData(RoomList rooms);
}


