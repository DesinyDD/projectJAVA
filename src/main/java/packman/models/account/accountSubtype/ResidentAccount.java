package packman.models.account.accountSubtype;

import javafx.scene.image.Image;
import packman.models.account.Account;
import packman.models.account.details.AccountType;
import packman.models.account.details.Name;
import packman.models.account.details.Picture;

import java.time.LocalDateTime;

public class ResidentAccount extends Account {
    private String roomNumber;

    /* Constructor */
    public ResidentAccount(Picture picture, LocalDateTime lastLogin, String username, String password, Name name, String roomNumber) {
        super(AccountType.RESIDENT, picture, lastLogin, username, password, name);
        this.roomNumber = roomNumber;
    }

    /* Setter */
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    /* Getter */
    public String getRoomNumber() { return roomNumber; }
}
