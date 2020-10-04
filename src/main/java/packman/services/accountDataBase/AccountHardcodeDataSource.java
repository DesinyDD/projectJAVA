package packman.services.accountDataBase;

import packman.models.account.AccountList;
import packman.models.account.accountSubtype.AdministerAccount;
import packman.models.account.accountSubtype.OfficerAccount;
import packman.models.account.accountSubtype.ResidentAccount;
import packman.models.account.details.Name;
import packman.models.account.details.Picture;

import java.time.LocalDateTime;

public class AccountHardcodeDataSource implements AccountDataSource {

    @Override
    public AccountList getAccountsData() {
        AccountList accounts = new AccountList();
        accounts.add(new ResidentAccount(new Picture("/images/default/muskPic.jpg"), LocalDateTime.of(2020,7,30,23,12,10), "Elon", "Elon", new Name("Elon", "Musk"), "567"));
        accounts.add(new ResidentAccount(new Picture("/images/default/jobsPic.jpg"), LocalDateTime.now(), "Jobs1", "Jobs", new Name("Steve", "Jobs"), "123"));
        accounts.add(new OfficerAccount(new Picture("/images/default/jobsPic.jpg"), LocalDateTime.now(), "Jobs", "Jobs", new Name("Steve", "Jobs"), true, 1));
        accounts.add(new AdministerAccount(new Picture("/images/default/jobsPic.jpg"), LocalDateTime.now(), "Jobs2", "Jobs", new Name("Steve", "Jobs")));
        return accounts;
    }

    @Override
    public void setAccountsData(AccountList accounts) {
        // do nothing
    }
}
