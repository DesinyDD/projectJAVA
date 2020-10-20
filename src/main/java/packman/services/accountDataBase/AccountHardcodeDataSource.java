package packman.services.accountDataBase;

import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.AdministratorAccount;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.accounts.subtypes.ResidentAccount;

import java.time.LocalDateTime;

public class AccountHardcodeDataSource implements AccountDataSource {

    @Override
    public AccountList getAccountsData() {
        AccountList accounts = new AccountList();
        accounts.add(new ResidentAccount("jobs", "jobs","Steve","Jobs","/images/default/avatars/jobsPic.jpg", LocalDateTime.now(), true, "B506",LocalDateTime.of(2019,5,23,10,26,47)));
        accounts.add(new OfficerAccount("gates", "gates", "Bill", "Gates", "/images/default/avatars/gatesPic.jpg", LocalDateTime.of(2018,4,2,14,1,16), false, 2));
        accounts.add(new OfficerAccount("cook", "cook", "Tim", "Cook", "/images/default/avatars/cookPic.jpg", LocalDateTime.of(2017,2,21,9,34,11), false, 1));
        accounts.add(new AdministratorAccount("musk", "musk", "Elon", "Musk", "/images/default/avatars/muskPic.jpg", LocalDateTime.of(2020,7,30,23,12,10)));

        return accounts;
    }

    @Override
    public void setAccountsData(AccountList accounts) {
        // do nothing
    }
}
