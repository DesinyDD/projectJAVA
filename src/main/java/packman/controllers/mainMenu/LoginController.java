package packman.controllers.mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packman.controllers.ResidentMailboxController;
import packman.models.account.Account;
import packman.models.account.AccountList;
import packman.models.account.details.AccountType;
import packman.models.building.RoomList;
import packman.models.mails.MailList;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.accountDataBase.AccountFileDataSource;
import packman.services.accountDataBase.AccountHardcodeDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.mailDataBase.MailHardcodeDataSource;
import packman.services.roomDataBase.RoomDataSource;
import packman.services.roomDataBase.RoomHardcodeDataSource;

import java.io.IOException;

public class LoginController {
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private RoomList rooms;
    private RoomDataSource roomDataSource;
    private MailList mails;
    private MailDataSource mailDataSource;
    private Account currentAccount;
    private String theme;

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setRooms(RoomList rooms) { this.rooms = rooms; }
    public void setRoomDataSource(RoomDataSource roomDataSource) { this.roomDataSource = roomDataSource; }
    public void setMails(MailList mails) { this.mails = mails; }
    public void setMailDataSource(MailDataSource mailDataSource) { this.mailDataSource = mailDataSource; }

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private Button devInfoButton;
    @FXML private Button helpButton;
    @FXML private Button themeButton;

    @FXML public void initialize() {
        this.accountDataSource = new AccountFileDataSource("data", "accounts.csv"); // new AccountHardcodeDataSource();
        this.accounts = accountDataSource.getAccountsData();
        this.roomDataSource = new RoomHardcodeDataSource(); // new RoomFileDataSource("data", "rooms.csv");
        this.rooms = roomDataSource.getRoomsData();
        this.mailDataSource = new MailHardcodeDataSource(); // new MailFileDataSource("data", "mails.csv");
        this.mails = mailDataSource.getMailsData();
        this.theme = "/stylesheets/lightTheme.css";
    }

    /* Login to PackMan */
    @FXML public void handleLoginButtonOnAction(ActionEvent event) throws IOException {
        if (accounts.login(usernameField.getText(), passwordField.getText())) {
            currentAccount = accounts.findByUsername(usernameField.getText());
            accounts.remove(currentAccount);
            currentAccount.setLastLogin();
            accounts.add(currentAccount);
            accountDataSource.setAccountsData(accounts);
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            /* Resident Login */
            if (currentAccount.getAccountType().equals(AccountType.RESIDENT)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/residentMenu/resident_mailbox_stage.fxml"));
                stage.setScene(new Scene(loader.load(), 1280, 720));
                ResidentMailboxController mailboxPage = loader.getController();
                mailboxPage.setCurrentAccount(currentAccount);
                stage.show();
            } else if (currentAccount.getAccountType().equals(AccountType.OFFICER)) {
                System.out.println("OFFICER");
                // In process . . .
            } else if (currentAccount.getAccountType().equals(AccountType.ADMINISTER)) {
                System.out.println("ADMIN");
                // In process . . .
            }
        }
    }

    /* Register for Resident Account */
    @FXML public void handleRegisterButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/resident_register_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        ResidentRegisterController registerPage = loader.getController();
        registerPage.setAccountDataSource(accountDataSource);
        registerPage.setRoomDataSource(roomDataSource);
        registerPage.setAccounts(accounts);
        registerPage.setRooms(rooms);
        registerPage.setTheme(theme);
        stage.show();
    }

    /* Developer Information */
    @FXML public void handleDevInfoButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/developer_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        DeveloperController developerPage = loader.getController();
        developerPage.setTheme(theme);
        stage.show();
    }

    /* Help Information */
    @FXML public void handleHelpButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/help_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        HelpController helpPage = loader.getController();
        helpPage.setTheme(theme);
        stage.show();
    }

    /* Change Theme */
    @FXML public void handleThemeButtonOnAction(ActionEvent event) throws IOException {
        if (theme.equals("/stylesheets/darkTheme.css")) {
            this.setTheme("/stylesheets/lightTheme.css");
        } else { this.setTheme("/stylesheets/darkTheme.css"); }
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/login_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        LoginController refreshPage = loader.getController();
        refreshPage.setTheme(theme);
        stage.show();
    }
}
