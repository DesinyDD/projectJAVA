package packman.models.mails.subtypes;

import packman.models.mails.Mail;
import packman.models.mails.details.Size2D;

import java.time.LocalDateTime;

public class Letter extends Mail {

    /* Constructor */
    public Letter(
            String receiverFirstName,
            String receiverLastName,
            String receiverRoomID,
            String senderFirstName,
            String senderLastName,
            String picPath,
            String receivingOfficerUsername,
            LocalDateTime receivingTime,
            String sendingOfficerUsername,
            LocalDateTime sendingTime,
            Size2D size2D) {
        super(
                receiverFirstName,
                receiverLastName,
                receiverRoomID,
                senderFirstName,
                senderLastName,
                size2D,
                picPath,
                receivingOfficerUsername,
                receivingTime,
                sendingOfficerUsername,
                sendingTime
        );
    }

    @Override
    public String getType() { return "Letter"; }
}
