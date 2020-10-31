package packman.models.mails;

import packman.models.mails.details.Size;
import packman.models.mails.subtypes.Document;
import packman.models.mails.subtypes.Letter;
import packman.models.mails.subtypes.Parcel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Mail implements Comparable {
    private String receiverFirstName;
    private String receiverLastName;
    private String receiverRoomID;
    private String senderFirstName;
    private String senderLastName;
    private Size size;
    private String picPath;
    private String receivingOfficerUsername;
    private LocalDateTime receivingTime;
    private String sendingOfficerUsername;
    private LocalDateTime sendingTime;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /* Constructor */
    public Mail(String receiverFirstName, String receiverLastName, String receiverRoomID, String senderFirstName, String senderLastName, Size size,
                String picPath, String receivingOfficerUsername, LocalDateTime receivingTime, String sendingOfficerUsername, LocalDateTime sendingTime) {
        this.setReceiverFirstName(receiverFirstName);
        this.setReceiverLastName(receiverLastName);
        this.setReceiverRoomID(receiverRoomID);
        this.setSenderFirstName(senderFirstName);
        this.setSenderLastName(senderLastName);
        this.setSize(size);
        this.setPicPath(picPath);
        this.setReceivingOfficerUsername(receivingOfficerUsername);
        this.setReceivingTime(receivingTime);
        this.setSendingOfficerUsername(sendingOfficerUsername);
        this.setSendingTime(sendingTime);
    }

    /* Setter */
    public void setReceiverFirstName(String receiverFirstName) { this.receiverFirstName = receiverFirstName; }
    public void setReceiverLastName(String receiverLastName) { this.receiverLastName = receiverLastName; }
    public void setReceiverRoomID(String receiverRoomID) { this.receiverRoomID = receiverRoomID; }
    public void setSenderFirstName(String senderFirstName) { this.senderFirstName = senderFirstName; }
    public void setSenderLastName(String senderLastName) { this.senderLastName = senderLastName; }
    public void setSize(Size size) { this.size = size; }
    public void setPicPath(String picPath) { this.picPath = picPath; }
    public void setReceivingOfficerUsername(String receivingOfficerUsername) { this.receivingOfficerUsername = receivingOfficerUsername; }
    public void setReceivingTime(LocalDateTime receivingTime) { this.receivingTime = receivingTime; }
    public void setSendingOfficerUsername(String sendingOfficerUsername) { this.sendingOfficerUsername = sendingOfficerUsername; }
    public void setSendingTime(LocalDateTime sendingTime) { this.sendingTime = sendingTime; }

    /* Getter */
    public String getReceiverFirstName() { return receiverFirstName; }
    public String getReceiverLastName() { return receiverLastName; }
    public String getReceiverRoomID() { return receiverRoomID; }
    public String getSenderFirstName() { return senderFirstName; }
    public String getSenderLastName() { return senderLastName; }
    public Size getSize() { return size; }
    public String getPicPath() { return picPath; }
    public String getReceivingOfficerUsername() { return receivingOfficerUsername; }
    public LocalDateTime getReceivingTime() { return receivingTime; }
    public String getSendingOfficerUsername() { return sendingOfficerUsername; }
    public LocalDateTime getSendingTime() { return sendingTime; }

    public String getSizeFormat() { return size.getSize(); }

    public String getCheckInOfficer() {
        return this.receivingOfficerUsername;
    }
    public String getCheckOutOfficer() {
        if (this.getSendingTime().isEqual(LocalDateTime.of(1,1,1,1,1,1)))
            return "Dismissed";
        return this.getSendingOfficerUsername();
    }
    public String getCheckIn() { return this.receivingTime.format(formatter); }
    public String getCheckOut() {
        if (this.getSendingTime().isEqual(LocalDateTime.of(1,1,1,1,1,1)))
            return "Dismissed";
        return this.getSendingTime().format(formatter);
    }

    public abstract String getType();
    public String getReceiverName() { return receiverFirstName + " " + receiverLastName; }
    public String getSenderName() { return senderFirstName + " " + senderLastName; }

    public double getLength() { return size.getLength(); }
    public double getWidth() { return size.getWidth(); }
    public double getHeight() { return size.getHeight(); }

    public boolean isLetter() { return this instanceof Letter; };
    public boolean isDocument() { return this instanceof Document; };
    public boolean isParcel() { return this instanceof Parcel; };

    public Letter toLetter() { return (Letter) this; }
    public Document toDocument() { return (Document) this; }
    public Parcel toParcel() { return (Parcel) this; }

    public double getVolume() { return size.getVolume(); }

    public boolean isForRoomID(String roomID) { return this.getReceiverRoomID().equals(roomID); }

    @Override
    public int compareTo(Object o) {
        Mail other = (Mail) o;
        if (this.receivingTime.isBefore(other.receivingTime)) { return 1;
        } else if (this.receivingTime.isAfter(other.receivingTime)) { return -1;
        } else {
            char thisTowerID  = this.receiverRoomID.charAt(0);
            char otherTowerID = this.receiverRoomID.charAt(0);
            if (thisTowerID < otherTowerID) { return -1;
            } else if (thisTowerID > otherTowerID) { return 1;
            } else {
                int thisRoomNumber  = Integer.parseInt(this.receiverRoomID.replaceAll("\\D+",""));
                int otherRoomNumber = Integer.parseInt(other.receiverRoomID.replaceAll("\\D+",""));
                if (thisRoomNumber < otherRoomNumber) { return -1;
                } else if (thisRoomNumber > otherRoomNumber) { return 1;
                } else { return 0; }
            }
        }
    }
}
