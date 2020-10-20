package packman.models.accounts.subtypes;

import packman.models.accounts.Account;

import java.time.LocalDateTime;

public class ResidentAccount extends Account {
    private boolean activate;
    private String roomID;
    private LocalDateTime enterDate;

    /* Constructor */
    public ResidentAccount(String username, String password, String firstName, String lastName, String picPath, LocalDateTime lastLogin, boolean activate, String roomID, LocalDateTime enterDate) {
        super(username, password, firstName, lastName, picPath, lastLogin);
        this.activate = activate;
        this.roomID = roomID;
        this.enterDate = enterDate;
    }

    /* Setter */
    public void setActivate(boolean activate) { this.activate = activate; }
    public void setRoomID(String roomID) { this.roomID = roomID; }
    public void setEnterDate(LocalDateTime enterDate) { this.enterDate = enterDate; }

    /* Getter */
    public boolean isActivate() { return activate; }
    public String getRoomID() { return roomID; }
    public LocalDateTime getEnterDate() { return enterDate; }

    public void deActivate() { this.activate = false; }

    public boolean isRoomID(String roomID) { return this.roomID.equals(roomID); }

    @Override
    public boolean isAvailable() { return this.isActivate(); }
}
