package packman.services.mailDataBase;

import packman.models.mails.MailList;
import packman.models.mails.details.*;
import packman.models.mails.subtypes.Letter;

import java.time.LocalDateTime;

public class MailHardcodeDataSource implements MailDataSource {

    @Override
    public MailList getMailsData() {
        MailList mails = new MailList();

        LocalDateTime receivingTime = LocalDateTime.of(2020,10,18,20,31,10);
        LocalDateTime sendingTime = LocalDateTime.of(2020,10,18,20,31,11);

        mails.add(new Letter("Steve", "Jobs", "B506", "Panupat", "Chu","image/mailPicture/brownLetter.jpg", "gates", receivingTime, "cook", sendingTime,  new Size2D(21, 29.7)));
//        mails.add(new Document(new Picture("/images/default/defaultMail.png"), new Name("Noppon", "Nonpob"), "123", new Name("Elon", "Monk"), new Size2D(6,4), new TimeStamp(new Name("Joh", "Jo"), LocalDateTime.now()), new TimeStamp(new Name("Nee", "No"), LocalDateTime.now()), DocumentType.EXPRESS));
//        mails.add(new Parcel(new Picture("/images/default/defaultMail.png"), new Name("Noppon", "Nonpob"), "123", new Name("Elon", "Monk"), new Size2D(6,4), new TimeStamp(new Name("Joh", "Jo"), LocalDateTime.now()), new TimeStamp(new Name("Nee", "No"), LocalDateTime.now()), CourierBrand.KERRY, "AWDF123AD"));
        return mails;
    }

    @Override
    public void setMailsData(MailList mails) {
        // do nothing
    }
}
