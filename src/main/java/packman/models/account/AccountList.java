package packman.models.account;

import packman.models.account.accountSubtype.ResidentAccount;
import packman.models.account.details.AccountType;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;

    /* Constructor */
    public AccountList() {
        this.accounts = new ArrayList<Account>();
    }

    /* Setter */
    public void setAccounts(ArrayList<Account> accounts) { this.accounts = accounts; }

    /* Getter */
    public ArrayList<Account> getAccounts() { return accounts; }

    public boolean add(Account account) { return accounts.add(account); }

    public boolean remove(Account account) { return accounts.remove(account); }

    public Account findByUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        throw new RuntimeException("Account with username " + username + " not found");
    }

    public ArrayList<Account> roomerIn(String roomNumber) {
        ArrayList<Account> tempAccounts = new ArrayList<Account>();
        for (Account account : accounts) {
            if (account.getAccountType() == AccountType.RESIDENT && ((ResidentAccount)account).getRoomNumber().equals(roomNumber)) {
                tempAccounts.add(account);
            }
        }
        return tempAccounts;
    }

    public boolean isUsed(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean login(String username, String password) {
        Account account = this.findByUsername(username);
        if (account.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public ArrayList<Account> toList() {
        return accounts;
    }

    public int totalRoomer(String roomNumber) { return roomerIn(roomNumber).size(); }

}
