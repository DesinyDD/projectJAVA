package packman.models.mails;

import packman.models.mails.information.Size;
import packman.models.mails.information.TimeStamp;
import packman.models.people.information.Receiver;
import packman.models.people.information.Sender;

public class Document extends Mail {
    private int priority;

    /* Constructor */
    public Document(int priority, Receiver receiver, Sender sender, Size size, TimeStamp receivingTime, TimeStamp sendingTime) {
        super(receiver, sender, size, receivingTime, sendingTime);
        this.priority = priority;
    }
    public Document(int priority, Receiver receiver, Sender sender, Size size, TimeStamp receivingTime) {
        this(priority, receiver, sender, size, receivingTime, null);
    }
    // public Document(int priority, Receiver receiver, Sender sender, Size size) {
    //     this(priority, receiver, sender, size, null);
    // }

    /* Setter */
    public void setPriority(int priority) { this.priority = priority; }

    /* Getter */
    public int getPriority() { return priority; }

    @Override
    public String getMailType() { return "Document"; }
}
