package packman.controllers.officerMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.buildings.RoomList;
import packman.models.mails.MailList;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.roomDataBase.RoomDataSource;

import java.io.IOException;

public class OfficerChangePassController {
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

    @FXML private PasswordField oldPassField;
    @FXML private PasswordField newPassField;
    @FXML private PasswordField reTypePassField;
    @FXML private Button donButton;
    @FXML private Label errorLabel;

    @FXML public void handleBackButtonOnAction(ActionEvent event) throws IOException { roomManagePage(event); }

    @FXML public void handleDoneButtonOnAction(ActionEvent event) throws IOException {
        if (currentAccount.isPassword(oldPassField.getText()) && !oldPassField.getText().isEmpty() && !newPassField.getText().isEmpty() &&
                newPassField.getText().equals(reTypePassField.getText()) && !reTypePassField.getText().isEmpty()) {
            currentAccount.setPassword(newPassField.getText());
            accountDataSource.setAccountsData(accounts);
            roomManagePage(event);
        } else {
            if (oldPassField.getText().isEmpty()) { errorLabel.setText("Please enter Old Password."); }
            else if (!currentAccount.isPassword(oldPassField.getText())) { errorLabel.setText("This Old Password is not correct. Try again."); }
            else if (newPassField.getText().isEmpty()) { errorLabel.setText("Please enter New Password."); }
            else if (reTypePassField.getText().isEmpty()) { errorLabel.setText("Please enter Retype New Password."); }
            else if (!newPassField.getText().equals(reTypePassField.getText())) { errorLabel.setText("Those passwords didn't match. Try again."); }
            else { errorLabel.setText("Something wrong. Try again."); }
        }
    }

    private void roomManagePage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/room_manage_stage.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        stage.getScene().getStylesheets().add(theme);
        RoomManageController roomManagePage = loader.getController();
        roomManagePage.setTheme(theme);
        roomManagePage.setAccountDataSource(accountDataSource);
        roomManagePage.setAccounts(accounts);
        roomManagePage.setRoomDataSource(roomDataSource);
        roomManagePage.setRooms(rooms);
        roomManagePage.setMailDataSource(mailDataSource);
        roomManagePage.setMails(mails);
        roomManagePage.setCurrentAccount(currentAccount);
        stage.show();
    }
}


