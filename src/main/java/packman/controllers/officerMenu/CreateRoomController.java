package packman.controllers.officerMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.buildings.Room;
import packman.models.buildings.RoomList;
import packman.models.buildings.subtypes.DuplexRoom;
import packman.models.buildings.subtypes.StudioRoom;
import packman.models.buildings.subtypes.SuiteRoom;
import packman.models.mails.MailList;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.roomDataBase.RoomDataSource;

import java.io.IOException;

public class CreateRoomController {
    private String theme;
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private RoomList rooms;
    private RoomDataSource roomDataSource;
    private MailList mails;
    private MailDataSource mailDataSource;
    private OfficerAccount currentAccount;

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setRooms(RoomList rooms) { this.rooms = rooms; }
    public void setRoomDataSource(RoomDataSource roomDataSource) { this.roomDataSource = roomDataSource; }
    public void setMails(MailList mails) { this.mails = mails; }
    public void setMailDataSource(MailDataSource mailDataSource) { this.mailDataSource = mailDataSource; }
    public void setCurrentAccount(OfficerAccount currentAccount) { this.currentAccount = currentAccount; }

    @FXML private Label roomIDLabel;
    @FXML private TextField towerIDField;
    @FXML private TextField floorNumberField;
    @FXML private TextField roomNumberField;
    @FXML private TextField roomTypeField;
    @FXML private Button addRoomButton;
    @FXML private Label errorLabel;
    @FXML private Button backButton;

    @FXML public void handleBackButtonOnAction(ActionEvent event) throws IOException { roomManagePage(event); }

    @FXML public void handleAddRoomButtonOnAction(ActionEvent event) throws IOException {
        String towerInput    = towerIDField.getText().trim().toUpperCase();
        String floorInput    = floorNumberField.getText().trim();
        String roomInput     = roomNumberField.getText().trim();
        String roomTypeInput = roomTypeField.getText().trim().toUpperCase();

        /* towerIDField --> ErrorReport */
        if (towerInput.isEmpty()) { errorLabel.setText("Please enter Tower ID."); }
        else if (towerInput.charAt(0) < 'A' || towerInput.charAt(0) > 'Z' || towerInput.length() > 1)
        { errorLabel.setText("Tower ID must have only one character. (without digit)"); }

        /* floorNumberField --> ErrorReport */
        else if (floorInput.isEmpty()) { errorLabel.setText("Please enter Floor Number."); }
        else { try {
            int floorData = Integer.parseInt(floorInput);
            if (floorData < 1) {
                errorLabel.setText("Floor Number must be greater than 0.");
            }

        /* roomNumberField --> ErrorReport */
            else if (roomInput.isEmpty()) {
                errorLabel.setText("Please enter Room Order.");
            } else { try { int roomData = Integer.parseInt(roomInput);
                if (roomData < 0) { errorLabel.setText("Room Order must be greater than -1."); }
                else if (roomData > 99) { errorLabel.setText("Room Order must be less than 100."); }

        /* roomTypeField --> ErrorReport */
                else {
                    if (roomTypeInput.isEmpty()) { errorLabel.setText("Please enter Room Type.");
                    } else if (roomTypeInput.equals("STUDIO") || roomTypeInput.equals("1")) {
                        String tempRoomOrder;
                        if (roomData < 10) { tempRoomOrder = String.format("0%d", roomData); }
                        else { tempRoomOrder = String.format("%d", roomData); }
                        Room newRoom = new StudioRoom(towerInput.charAt(0), floorData, tempRoomOrder);
                        try {
                            rooms.getRoom(newRoom.getRoomID());
                            errorLabel.setText("This Room ID already registered.");
                        } catch (Exception e) {
                            rooms.add(newRoom);
                            roomDataSource.setRoomsData(rooms);
                            roomManagePage(event);
                        }
                    } else if (roomTypeInput.equals("SUITE") || roomTypeInput.equals("2")) {
                        String tempRoomOrder;
                        if (roomData < 10) { tempRoomOrder = String.format("0%d", roomData); }
                        else { tempRoomOrder = String.format("%d", roomData); }
                        Room newRoom = new SuiteRoom(towerInput.charAt(0), floorData, tempRoomOrder);
                        try {
                            rooms.getRoom(newRoom.getRoomID());
                            errorLabel.setText("This Room ID already registered.");
                        } catch (Exception e) {
                            rooms.add(newRoom);
                            roomDataSource.setRoomsData(rooms);
                            roomManagePage(event);
                        }
                    } else if (roomTypeInput.equals("DUPLEX") || roomTypeInput.equals("4")) {
                        String tempRoomOrder;
                        if (roomData < 10) { tempRoomOrder = String.format("0%d", roomData); }
                        else { tempRoomOrder = String.format("%d", roomData); }
                        Room newRoom = new DuplexRoom(towerInput.charAt(0), floorData, tempRoomOrder);
                        try {
                            rooms.getRoom(newRoom.getRoomID());
                            errorLabel.setText("This Room ID already registered.");
                        } catch (Exception e) {
                            rooms.add(newRoom);
                            roomDataSource.setRoomsData(rooms);
                            roomManagePage(event);
                        }
                    } else { errorLabel.setText("Room Type must be \"STUDIO\", \"SUITE\" or \"DUPLEX\"."); } }
                } catch(Exception e) { errorLabel.setText("Room Order must have only digit."); } }
            } catch(Exception e) { errorLabel.setText("Floor Number must have only digit."); }
        }
    }

    private void roomManagePage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/room_manage_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        RoomManageController roomManagePage = loader.getController();
        roomManagePage.setTheme(theme);
        roomManagePage.setAccountDataSource(accountDataSource);
        roomManagePage.setAccounts(accounts);
        roomManagePage.setMailDataSource(mailDataSource);
        roomManagePage.setMails(mails);
        roomManagePage.setRoomDataSource(roomDataSource);
        roomManagePage.setRooms(rooms);
        roomManagePage.setCurrentAccount(currentAccount);
        stage.show();
    }
}
