package packman.models.people.information;

import packman.models.people.Resident;
import packman.models.residences.Room;

public class Receiver {
    private Resident receiver;
    private Room receiverRoom;

    /* Constructor */
    public Receiver(Resident receiver, Room receiverRoom) {
        this.receiver     = receiver;
        this.receiverRoom = receiverRoom;
    }

    /* Setter */
    public void setReceiver(Resident receiver) { this.receiver = receiver; }
    public void setReceiverRoom(Room receiverRoom) { this.receiverRoom = receiverRoom; }

    /* Getter */
    public Resident getReceiver() { return receiver; }
    public Room getReceiverRoom() { return receiverRoom; }
}
