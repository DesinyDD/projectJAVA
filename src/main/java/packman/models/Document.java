package packman.models;

public class Document extends Mail {
    private int priority;

    Document(Resident receiver, String senderName, int mailWidht, int mailLength, int priority) {
        super(receiver, senderName, mailWidht, mailLength);
        this.priority = priority;
    }

    public int getPriority() { return priority; }
}
