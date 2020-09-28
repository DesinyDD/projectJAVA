package packman.models.mails;

import packman.models.mails.information.Size;
import packman.models.mails.information.TimeStamp;
import packman.models.people.information.Receiver;
import packman.models.people.information.Sender;

public class Parcel extends Mail {
    private String courierName;
    private String trackingNumber;

    /* Constructor */
    public Parcel(String courierName, String trackingNumber, Receiver receiver, Sender sender, Size size, TimeStamp receivingTime, TimeStamp sendingTime) {
        super(receiver, sender, size, receivingTime, sendingTime);
        this.courierName = courierName;
        this.trackingNumber = trackingNumber;
    }
    public Parcel(String courierName, String trackingNumber, Receiver receiver, Sender sender, Size size, TimeStamp receivingTime) {
        this(courierName, trackingNumber, receiver, sender, size, receivingTime, null);
    }
    // public Parcel(String courierName, String trackingNumber, Receiver receiver, Sender sender, Size size) {
    //     this(courierName, trackingNumber, receiver, sender, size, null);
    // }

    /* Setter */
    public void setCourierName(String courierName) { this.courierName = courierName; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    /* Getter */
    public String getCourierName() { return courierName; }
    public String getTrackingNumber() { return trackingNumber; }

    @Override
    public String getMailType() { return "Parcel"; }
}
