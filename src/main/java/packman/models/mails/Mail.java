package packman.models.mails;

import javafx.scene.image.Image;
import packman.models.account.details.Name;
import packman.models.account.details.Picture;
import packman.models.mails.details.MailType;
import packman.models.mails.details.Size;
import packman.models.mails.details.TimeStamp;

public class Mail {
    private MailType mailType;
    private Picture picture;
    private Name   receiverName;
    private String receiverRoom;
    private Name   senderName;
    private Size   size;
    private TimeStamp receivingStamp;
    private TimeStamp sendingStamp;

    /* Constructor */
    public Mail(MailType mailType, Picture picture, Name receiverName, String receiverRoom, Name senderName, Size size, TimeStamp receivingStamp, TimeStamp sendingStamp) {
        this.mailType       = mailType;
        this.picture        = picture;
        this.receiverName   = receiverName;
        this.receiverRoom   = receiverRoom;
        this.senderName     = senderName;
        this.size           = size;
        this.receivingStamp = receivingStamp;
        this.sendingStamp   = sendingStamp;
    }

    /* Setter */
    public void setMailType(MailType mailType) { this.mailType = mailType; }
    public void setPicture(Picture picture) { this.picture = picture; }
    public void setReceiverName(Name receiverName) { this.receiverName = receiverName; }
    public void setReceiverRoom(String receiverRoom) { this.receiverRoom = receiverRoom; }
    public void setSenderName(Name senderName) { this.senderName = senderName; }
    public void setSize(Size size) { this.size = size; }
    public void setReceivingStamp(TimeStamp receivingStamp) { this.receivingStamp = receivingStamp; }
    public void setSendingStamp(TimeStamp sendingStamp) { this.sendingStamp = sendingStamp; }

    /* Getter */
    public MailType getMailType() { return mailType; }
    public Picture getPicture() { return picture; }
    public Name getReceiverName() { return receiverName; }
    public String getReceiverRoom() { return receiverRoom; }
    public Name getSenderName() { return senderName; }
    public Size getSize() { return size; }
    public TimeStamp getReceivingStamp() { return receivingStamp; }
    public TimeStamp getSendingStamp() { return sendingStamp; }

    public Image getImage() { return picture.getImage(); }
    public String getImagePath() { return picture.getPath(); }
    public Double getVolume() { return size.getVolume(); }
}
