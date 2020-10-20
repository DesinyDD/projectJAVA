package packman.services.mailDataBase;

import packman.models.accounts.AccountList;
import packman.models.buildings.RoomList;
import packman.models.mails.MailList;

public interface MailDataSource {
    MailList getMailsData();

    void setMailsData(MailList mails);
}
