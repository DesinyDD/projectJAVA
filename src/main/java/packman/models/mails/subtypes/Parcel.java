package packman.models.mails.subtypes;

import packman.models.mails.Mail;
import packman.models.mails.details.CourierBrand;
import packman.models.mails.details.Size3D;

import java.time.LocalDateTime;

public class Parcel extends Mail {
    private CourierBrand courierBrand;
    private String trackingNumber;

    /* Constructor */
    public Parcel(String receiverFirstName, String receiverLastName, String receiverRoomID, String senderFirstName, String senderLastName, Size3D size3D, String picPath, String receivingOfficerUsername, LocalDateTime receivingTime, String sendingOfficerUsername, LocalDateTime sendingTime, CourierBrand courierBrand, String trackingNumber) {
        super(receiverFirstName, receiverLastName, receiverRoomID, senderFirstName, senderLastName, size3D, picPath, receivingOfficerUsername, receivingTime, sendingOfficerUsername, sendingTime);
        this.courierBrand = courierBrand;
        this.trackingNumber = trackingNumber;
    }

    /* Setter */
    public void setCourierBrand(CourierBrand courierBrand) { this.courierBrand = courierBrand; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    /* Getter */
    public CourierBrand getCourierBrand() { return courierBrand; }
    public String getTrackingNumber() { return trackingNumber; }
}

