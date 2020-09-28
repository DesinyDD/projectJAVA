package packman.models.mails;

import packman.models.mails.information.Size;
import packman.models.mails.information.TimeStamp;
import packman.models.people.information.Address;
import packman.models.people.information.Name;
import packman.models.people.information.Receiver;
import packman.models.people.information.Sender;
import packman.models.residences.Room;

public class Mail {
    private Receiver  receiver;
    private Sender    sender;
    private Size      size;
    private TimeStamp receivingStamp;
    private TimeStamp sendingStamp;

    /* Constructor */
    public Mail(Receiver receiver, Sender sender, Size size, TimeStamp receivingStamp, TimeStamp sendingStamp) {
        this.receiver       = receiver;
        this.sender         = sender;
        this.size           = size;
        this.receivingStamp = receivingStamp;
        this.sendingStamp   = sendingStamp;
    }
    public Mail(Receiver receiver, Sender sender, Size size, TimeStamp receivingStamp) {
        this(receiver, sender, size, receivingStamp, null);
    }
    // public Mail(Receiver receiver, Sender sender, Size size) {
    //     this(receiver, sender, size, null);
    // }

    /* Setter */
    public void setReceiver(Receiver receiver) { this.receiver = receiver; }
    public void setSender(Sender sender) { this.sender = sender; }
    public void setSize(Size size) { this.size = size; }
    public void setReceiving(TimeStamp receiving) { this.receivingStamp = receiving; }
    public void setSendingStamp(TimeStamp sendingStamp) { this.sendingStamp = sendingStamp; }

    /* Getter */
    public Receiver getReceiver() { return receiver; }
    public Sender getSender() { return sender; }
    public Size getSize() { return size; }
    public TimeStamp getReceivingStamp() { return receivingStamp; }
    public TimeStamp getSendingStamp() { return sendingStamp; }

    public Name getReceiverName() { return receiver.getReceiver().getName(); }
    public Room getReceiverRoom() { return receiver.getReceiver().getResidence(); }
    public Name getSenderName() { return sender.getSenderName(); }
    public Address getSenderAddress() { return sender.getSenderAddress(); }
    public Double getVolume() { return size.getVolume(); }

    public void received(TimeStamp sendingStamp) { this.sendingStamp = sendingStamp; }

    public boolean isReceived() { return sendingStamp != null; }

    public String getMailType() { return "Mail"; }
}
