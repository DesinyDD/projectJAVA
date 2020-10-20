package packman.models.mails.subtypes;

import packman.models.mails.Mail;
import packman.models.mails.details.DocumentType;
import packman.models.mails.details.Size2D;

import java.time.LocalDateTime;

public class Document extends Mail {
    private DocumentType documentType;

    /* Constructor */
    public Document(String receiverFirstName, String receiverLastName, String receiverRoomID, String senderFirstName, String senderLastName, Size2D size2D, String picPath, String receivingOfficerUsername, LocalDateTime receivingTime, String sendingOfficerUsername, LocalDateTime sendingTime, DocumentType documentType) {
        super(receiverFirstName, receiverLastName, receiverRoomID, senderFirstName, senderLastName, size2D, picPath, receivingOfficerUsername, receivingTime, sendingOfficerUsername, sendingTime);
        this.documentType = documentType;
    }

    /* Setter */
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    /* Getter */
    public DocumentType getDocumentType() { return documentType; }
}
