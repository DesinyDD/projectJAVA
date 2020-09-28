package packman.models.people.information;

public class Sender {
    private Name senderName;
    private Address senderAddress;

    /* Constructor */
    public Sender(Name senderName, Address senderAddress) {
        this.senderName    = senderName;
        this.senderAddress = senderAddress;
    }
    public Sender(Name senderName) {
        this(senderName, null);
    }

    /* Setter */
    public void setSenderName(Name senderName) { this.senderName = senderName; }
    public void setSenderAddress(Address senderAddress) { this.senderAddress = senderAddress; }

    /* Getter */
    public Name getSenderName() { return senderName; }
    public Address getSenderAddress() { return senderAddress; }
}
