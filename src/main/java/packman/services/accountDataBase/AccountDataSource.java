package packman.services.accountDataBase;

import packman.models.accounts.AccountList;
import packman.models.buildings.RoomList;

public interface AccountDataSource {
    AccountList getAccountsData();

    void setAccountsData(AccountList accounts);
}
