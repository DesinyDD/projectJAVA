package packman.services.roomDataBase;

import packman.models.buildings.Room;
import packman.models.buildings.RoomList;
import packman.models.buildings.subtypes.DuplexRoom;
import packman.models.buildings.subtypes.StudioRoom;
import packman.models.buildings.subtypes.SuiteRoom;

import java.io.*;

public class RoomFileDataSource implements RoomDataSource {
    private String fileDirectoryName;
    private String fileName;
    private RoomList rooms;

    public RoomFileDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    @Override
    public RoomList getRoomsData() {
        try {
            rooms = new RoomList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return rooms;
    }

    private void readData() throws IOException {
        String filePath       = fileDirectoryName + File.separator + fileName;
        File file             = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line           = "";

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            /* StudioRoom */
            if (data[0].trim().equals("1")) {
                String towerID     = data[1].trim();
                String floorNumber = data[2].trim();
                String roomNumber  = data[3].trim();
                Room room = new StudioRoom(towerID, floorNumber, roomNumber);
                rooms.add(room);
            }

            /* SuiteRoom */
            else if (data[0].trim().equals("2")) {
                String towerID     = data[1].trim();
                String floorNumber = data[2].trim();
                String roomNumber  = data[3].trim();
                Room room = new SuiteRoom(towerID, floorNumber, roomNumber);
                rooms.add(room);
            }

            /* DuplexRoom */
            else if (data[0].trim().equals("4")) {
                String towerID     = data[1].trim();
                String floorNumber = data[2].trim();
                String roomNumber  = data[3].trim();
                Room room = new DuplexRoom(towerID, floorNumber, roomNumber);
                rooms.add(room);
            }
        }
        reader.close();
    }

    @Override
    public void setRoomsData(RoomList rooms) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Room room: rooms.toArrayList()) {
                String line = new String();
                line = room.getMaxRoomer() + "," + room.getTowerID() + "," +
                        room.getFloorNumber() + "," + room.getRoomNumber();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
