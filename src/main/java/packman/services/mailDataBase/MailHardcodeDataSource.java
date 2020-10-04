package packman.services.mailDataBase;

import packman.models.account.details.Name;
import packman.models.account.details.Picture;
import packman.models.mails.Mail;
import packman.models.mails.MailList;
import packman.models.mails.details.MailType;
import packman.models.mails.details.Size;
import packman.models.mails.details.TimeStamp;

import java.time.LocalDateTime;

public class MailHardcodeDataSource implements MailDataSource {

    @Override
    public MailList getMailsData() {
        MailList mails = new MailList();
        mails.add(new Mail(MailType.MAIL, new Picture("/images/default/defaultMail.png"), new Name("Noppon", "Nonpob"), "123", new Name("Elon", "Monk"), new Size(6,4), new TimeStamp(new Name("Joh", "Jo"), LocalDateTime.now()), new TimeStamp(new Name("Nee", "No"), LocalDateTime.now())));
        return mails;
    }

    @Override
    public void setMailsData(MailList mails) {
        // do nothing
    }
}
