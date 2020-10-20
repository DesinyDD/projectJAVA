package packman.models.mails;

import packman.models.mails.details.Size;

import java.time.LocalDateTime;

public abstract class Mail {
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

    /* Constructor */
    public Mail(String receiverFirstName, String receiverLastName, String receiverRoomID, String senderFirstName, String senderLastName, Size size, String picPath, String receivingOfficerUsername, LocalDateTime receivingTime, String sendingOfficerUsername, LocalDateTime sendingTime) {
        this.receiverFirstName        = receiverFirstName;
        this.receiverLastName         = receiverLastName;
        this.receiverRoomID           = receiverRoomID;
        this.senderFirstName          = senderFirstName;
        this.senderLastName           = senderLastName;
        this.size                     = size;
        this.picPath                  = picPath;
        this.receivingOfficerUsername = receivingOfficerUsername;
        this.receivingTime            = receivingTime;
        this.sendingOfficerUsername   = sendingOfficerUsername;
        this.sendingTime              = sendingTime;
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

    public Double getVolume() { return size.getVolume(); }

    public boolean isForRoomID(String roomID) { return this.getReceiverRoomID().equals(roomID); }
}
