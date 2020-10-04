package packman.models.mails.mailSubtype;

import packman.models.account.details.Name;
import packman.models.account.details.Picture;
import packman.models.mails.Mail;
import packman.models.mails.details.DocumentType;
import packman.models.mails.details.MailType;
import packman.models.mails.details.Size;
import packman.models.mails.details.TimeStamp;

public class Document extends Mail {
    private DocumentType documentType;

    public Document(Picture picture, Name receiverName, String receiverRoom, Name senderName, Size size, TimeStamp receivingStamp, TimeStamp sendingStamp, DocumentType documentType) {
        super(MailType.DOCUMENT, picture, receiverName, receiverRoom, senderName, size, receivingStamp, sendingStamp);
        this.documentType = documentType;
    }

    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public DocumentType getDocumentType() { return documentType; }
}
