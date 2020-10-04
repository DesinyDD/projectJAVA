package packman.models.mails;

import packman.models.account.details.Name;
import packman.models.account.details.Picture;
import packman.models.mails.details.CourierBrand;
import packman.models.mails.details.MailType;
import packman.models.mails.details.Size;
import packman.models.mails.details.TimeStamp;

public class Parcel extends Mail {
    private CourierBrand courierBrand;
    private String trackingNumber;

    public Parcel(Picture picture, Name receiverName, String receiverRoom, Name senderName, Size size, TimeStamp receivingStamp, TimeStamp sendingStamp, CourierBrand courierBrand, String trackingNumber) {
        super(MailType.PARCEL, picture, receiverName, receiverRoom, senderName, size, receivingStamp, sendingStamp);
        this.courierBrand = courierBrand;
        this.trackingNumber = trackingNumber;
    }

    public void setCourierBrand(CourierBrand courierBrand) { this.courierBrand = courierBrand; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public CourierBrand getCourierBrand() { return courierBrand; }
    public String getTrackingNumber() { return trackingNumber; }
}

