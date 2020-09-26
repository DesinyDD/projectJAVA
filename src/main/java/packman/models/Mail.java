package packman.models;

public class Mail {
    private Resident receiver;
    private String senderName;
    private int mailWidht;
    private int mailLength;

    Mail(Resident receiver, String senderName, int mailWidht, int mailLength) {
        this.receiver   = receiver;
        this.senderName = senderName;
        this.mailWidht  = mailWidht;
        this.mailLength = mailLength;
    }

    public Resident getReceiver() { return receiver; }

    public String getSenderName() { return senderName; }

    public int getMailWidht() { return mailWidht; }

    public int getMailLength() { return mailLength; }

}
