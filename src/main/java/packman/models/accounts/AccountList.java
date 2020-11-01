package packman.models.accounts;

import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.accounts.subtypes.ResidentAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class AccountList {
    private Collection<Account> accounts;

    /* Constructor */
    public AccountList() {
        this.accounts = new ArrayList<>();
    }

    /* Setter */
    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    /* Getter */
    public Collection<Account> getAccounts() {
        return accounts;
    }

    public boolean add(Account account) {
        return accounts.add(account);
    }

    public boolean remove(Account account) {
        return accounts.remove(account);
    }

    public Account findAccount(String username) {
        for (Account account : accounts) {
            if (account.isUsername(username)) {
                return account;
            }
        }
        throw new RuntimeException("Account with username " + username + " not found");
    }

    public ResidentAccount findResident(String username) {
        for (Account account : accounts) {
            if (account.isUsername(username) && account.isResident()) {
                return (ResidentAccount) account;
            }
        }
        throw new RuntimeException("Account with username " + username + " not found");
    }

    public OfficerAccount findOfficer(String username) {
        for (Account account : accounts) {
            if (account.isUsername(username) && account.isOfficer()) {
                return (OfficerAccount) account;
            }
        }
        throw new RuntimeException("Account with username " + username + " not found");
    }

    public ArrayList<Account> getRooms(String roomerName) {
        ArrayList<Account> rooms = new ArrayList<>();
        for (Account account : accounts) {
            if (account.isResident() && account.toResident().getFullName().toUpperCase().contains(roomerName.toUpperCase())) {
                rooms.add(account);
            }
        }
        return rooms;
    }

    public Collection<Account> getRoomers(String roomID) {
        Collection<Account> roomers = new ArrayList<>();
        for (Account account : accounts) {
            if (account.isResident() && account.isAvailable() && account.toResident().isRoomID(roomID)) {
                roomers.add(account.toResident());
            }
        }
        return roomers;
    }

    public Collection<Account> getOfficers() {
        Collection<Account> officers = new ArrayList<>();
        for (Account account : accounts) {
            if (account.isOfficer()) {
                officers.add(account.toOfficer());
            }
        }
        return officers;
    }

    public Collection<Account> getResidents() {
        Collection<Account> residents = new ArrayList<>();
        for (Account account : accounts) {
            if (account.isResident()) {
                residents.add(account.toResident());
            }
        }
        return residents;
    }

    public boolean isUsernameUsed(String username) {
        for (Account account : accounts) {
            if (account.isUsername(username)) {
                return true;
            }
        }
        return false;
    }

    public Account login(String username, String password) {
        for (Account account : accounts) {
            if (account.validation(username, password)) {
                return account;
            }
        }
        throw new RuntimeException("Username or Password is incorrect");
    }

    public ArrayList<Account> toArrayList() {
        return (ArrayList<Account>) accounts;
    }

    public int totalRoomer(String roomNumber) {
        return this.getRoomers(roomNumber).size();
    }
}