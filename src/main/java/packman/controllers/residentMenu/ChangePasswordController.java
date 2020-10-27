package packman.controllers.residentMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.ResidentAccount;
import packman.models.buildings.RoomList;
import packman.models.mails.MailList;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.roomDataBase.RoomDataSource;

import java.io.IOException;

public class ChangePasswordController {
    private String            theme;
    private AccountList       accounts;
    private AccountDataSource accountDataSource;
    private RoomList          rooms;
    private RoomDataSource    roomDataSource;
    private MailList          mails;
    private MailDataSource    mailDataSource;
    private ResidentAccount   currentAccount;

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setRooms(RoomList rooms) { this.rooms = rooms; }
    public void setRoomDataSource(RoomDataSource roomDataSource) { this.roomDataSource = roomDataSource; }
    public void setMails(MailList mails) { this.mails = mails; }
    public void setMailDataSource(MailDataSource mailDataSource) { this.mailDataSource = mailDataSource; }
    public void setCurrentAccount(ResidentAccount account) { this.currentAccount = account; }

    @FXML Label         titlePageLabel;
    @FXML PasswordField oldPasswordField;
    @FXML PasswordField newPasswordField;
    @FXML PasswordField retypeNewPasswordField;
    @FXML Button        changePasswordButton;
    @FXML Label         errorLabel;
    @FXML Button        backButton;

    @FXML public void handleChangePasswordButtonOnAction(ActionEvent event) throws IOException {
        String oldPasswordInput       = oldPasswordField.getText().trim();
        String newPasswordInput       = newPasswordField.getText().trim();
        String retypeNewPasswordInput = retypeNewPasswordField.getText().trim();

        if (oldPasswordInput.isEmpty()) { errorLabel.setText("Please enter Old Password.");
        } else if (!currentAccount.isPassword(oldPasswordInput)) { errorLabel.setText("This Old Password is not correct.");
        } else if (newPasswordInput.isEmpty()) { errorLabel.setText("Please enter New Password.");
        } else if (retypeNewPasswordInput.isEmpty()) { errorLabel.setText("Please enter Retype New Password.");
        } else if (!newPasswordInput.equals(retypeNewPasswordInput)) { errorLabel.setText("Those passwords didn't match. Try again.");
        } else {
            currentAccount.setPassword(newPasswordInput);
            accountDataSource.setAccountsData(accounts);
            residentMailBokPage(event);
        }
    }

    @FXML public void handleBackButtonOnAction(ActionEvent event) throws IOException { residentMailBokPage(event); }

    private void residentMailBokPage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/residentMenu/resident_mailbox_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        ResidentMailboxController residentMailBokPage = loader.getController();
        residentMailBokPage.setTheme(theme);
        residentMailBokPage.setAccounts(accounts);
        residentMailBokPage.setAccountDataSource(accountDataSource);
        residentMailBokPage.setRooms(rooms);
        residentMailBokPage.setRoomDataSource(roomDataSource);
        residentMailBokPage.setMails(mails);
        residentMailBokPage.setMailDataSource(mailDataSource);
        residentMailBokPage.setCurrentAccount(currentAccount);
        stage.show();
    }
}
