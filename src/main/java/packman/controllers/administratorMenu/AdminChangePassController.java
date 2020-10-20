package packman.controllers.administratorMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.AdministratorAccount;
import packman.services.accountDataBase.AccountDataSource;

import java.io.IOException;

public class AdminChangePassController {
    private String theme;
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private AdministratorAccount currentAccount;

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setCurrentAccount(AdministratorAccount currentAccount) { this.currentAccount = currentAccount; }

    @FXML private PasswordField oldPassField;
    @FXML private PasswordField newPassField;
    @FXML private PasswordField reNewPassField;
    @FXML private Button doneButton;
    @FXML private Label errorLabel;

    @FXML public void handleDoneButtonOnAction(ActionEvent event) throws IOException {
        if (currentAccount.isPassword(oldPassField.getText()) && newPassField.getText().equals(reNewPassField.getText())) {
            currentAccount.setPassword(newPassField.getText());
            accountDataSource.setAccountsData(accounts);
            handleBackButtonOnAction(event);
        } else {
            if (oldPassField.getText().isEmpty()) { errorLabel.setText("Please enter old password."); }
            else if (newPassField.getText().isEmpty()) { errorLabel.setText("Please enter new password."); }
            else if (reNewPassField.getText().isEmpty()) { errorLabel.setText("Please enter re-enter new password."); }
            else if (!currentAccount.isPassword(oldPassField.getText())) { errorLabel.setText("This old is not correct. Try again."); }
            else if (!newPassField.getText().equals(reNewPassField.getText())) { errorLabel.setText("Those passwords didn't match. Try again."); }
            else { errorLabel.setText("Something wrong. Try again."); }
        }
    }

    @FXML public void handleBackButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/administratorMenu/administrator_stage.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        stage.getScene().getStylesheets().add(theme);
        AdministratorController adminPage = loader.getController();
        adminPage.setTheme(theme);
        adminPage.setAccountDataSource(accountDataSource);
        adminPage.setAccounts(accounts);
        adminPage.setCurrentAccount(currentAccount);
        stage.show();
    }
}
