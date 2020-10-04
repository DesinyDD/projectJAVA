package packman.services.accountDataBase;

import packman.models.account.AccountList;

public interface AccountDataSource {
    AccountList getAccountsData();

    void setAccountsData(AccountList accounts);
}
