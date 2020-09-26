package packman.models;

public class Parcel extends Mail {
    private int mailHeight;
    private String courierName;
    private String trackingNumber;

    Parcel(Resident receiver, String senderName, int mailWidht, int mailLength, int mailHeight, String courierName, String trackingNumber) {
        super(receiver, senderName, mailWidht, mailLength);
        this.courierName    = courierName;
        this.trackingNumber = trackingNumber;
    }

    public int getMailHeight() { return mailHeight; }

    public String getCourierName() { return courierName; }

    public String getTrackingNumber() { return trackingNumber; }
}
