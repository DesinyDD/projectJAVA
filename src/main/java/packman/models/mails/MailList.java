package packman.models.mails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class MailList {
    private Collection<Mail> mails;

    /* Constructor */
    public MailList() { this.mails = new ArrayList<>(); }

    /* Setter */
    public void setMails(Collection<Mail> mails) { this.mails = mails; }

    /* Getter */
    public Collection<Mail> getMails() { return mails; }

    public boolean add(Mail mail) { return mails.add(mail); }

    public ArrayList<Mail> getMails(String roomID) {
        ArrayList<Mail> mailBox = new ArrayList<>();
        for (Mail mail : mails) {
            if (mail.isForRoomID(roomID))
                mailBox.add(mail);
        }
        return mailBox;
    }

    public ArrayList<Mail> getDisMissedMails() {
        ArrayList<Mail> disMissedMails = new ArrayList<>();
        for (Mail mail : mails)
            if (mail.getSendingTime().isEqual(LocalDateTime.of(1,1,1,1,1,1))) {
                disMissedMails.add(mail);
        }
        return disMissedMails;
    }

    public ArrayList<Mail> getAcceptedMails() {
        ArrayList<Mail> acceptedMails = new ArrayList<>();
        for (Mail mail : mails) {
            if (!mail.getSendingTime().isEqual(LocalDateTime.of(1,1,1,1,1,1)))
                acceptedMails.add(mail);
        }
        return acceptedMails;
    }

    public ArrayList<Mail> toArrayList() { return (ArrayList<Mail>) mails; }
}
