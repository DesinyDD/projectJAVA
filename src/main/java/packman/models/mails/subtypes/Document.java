package packman.models.mails.subtypes;

import packman.models.mails.Mail;
import packman.models.mails.details.Size2D;

import java.time.LocalDateTime;

public class Document extends Mail {
    private String documentType;

    /* Constructor */
    public Document(String receiverFirstName,
                    String receiverLastName,
                    String receiverRoomID,
                    String senderFirstName,
                    String senderLastName,
                    String picPath,
                    String receivingOfficerUsername,
                    LocalDateTime receivingTime,
                    String sendingOfficerUsername,
                    LocalDateTime sendingTime,
                    Size2D size2D,
                    String documentType) {
        super(receiverFirstName, receiverLastName, receiverRoomID, senderFirstName, senderLastName, size2D, picPath, receivingOfficerUsername, receivingTime, sendingOfficerUsername, sendingTime);
        this.documentType = documentType;
    }

    /* Setter */
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    /* Getter */
    public String getDocumentType() { return documentType; }

    @Override
    public String getType() { return "Document"; }
}
