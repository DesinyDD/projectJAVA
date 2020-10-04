package packman.models.mails;

import java.util.ArrayList;

public class MailList {
    private ArrayList<Mail> mails;

    /* Constructor */
    public MailList() { this.mails = new ArrayList<Mail>(); }

    /* Setter */
    public void setMails(ArrayList<Mail> mails) { this.mails = mails; }

    /* Getter */
    public ArrayList<Mail> getMails() { return mails; }

    public boolean add(Mail mail) { return mails.add(mail); }
}
