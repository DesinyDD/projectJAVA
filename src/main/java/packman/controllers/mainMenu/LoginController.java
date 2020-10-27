package packman.controllers.mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import packman.controllers.administratorMenu.AdministratorController;
import packman.controllers.officerMenu.RoomManageController;
import packman.controllers.residentMenu.ResidentMailboxController;
import packman.models.accounts.Account;
import packman.models.accounts.AccountList;
import packman.models.buildings.RoomList;
import packman.models.mails.MailList;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.accountDataBase.AccountFileDataSource;
import packman.services.accountDataBase.AccountHardcodeDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.mailDataBase.MailFileDataSource;
import packman.services.mailDataBase.MailHardcodeDataSource;
import packman.services.roomDataBase.RoomDataSource;
import packman.services.roomDataBase.RoomFileDataSource;
import packman.services.roomDataBase.RoomHardcodeDataSource;

import java.io.IOException;

public class LoginController {
    private String theme;
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private RoomList rooms;
    private RoomDataSource roomDataSource;
    private MailList mails;
    private MailDataSource mailDataSource;
    private Account currentAccount;

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
    @FXML private ImageView themeIcon;
    @FXML private Label errorAlert;

    @FXML public void initialize() {
        this.accountDataSource = new AccountFileDataSource("data", "accounts.csv"); // new AccountHardcodeDataSource();
        this.accounts          = accountDataSource.getAccountsData();
        this.roomDataSource    = new RoomFileDataSource("data", "rooms.csv"); // new RoomHardcodeDataSource();
        this.rooms             = roomDataSource.getRoomsData();
        this.mailDataSource    = new MailFileDataSource("data", "mails.csv"); // new MailHardcodeDataSource();
        this.mails             = mailDataSource.getMailsData();
        this.theme             = "/stylesheets/lightTheme.css";
    }

    /* Login to PackMan */
    @FXML public void handleLoginButtonOnAction(ActionEvent event) {
        if (usernameField.getText().isEmpty()) {
            errorAlert.setText("Please enter Username.");
        } else if (passwordField.getText().isEmpty()) {
            errorAlert.setText("Please enter Password.");
        } else {
            try {
                currentAccount = accounts.login(usernameField.getText(), passwordField.getText());
                accountDataSource.setAccountsData(accounts);
                Button button = (Button) event.getSource();
                Stage stage = (Stage) button.getScene().getWindow();

                if (currentAccount.isAvailable()) {
                    if (currentAccount.isResident()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/residentMenu/resident_mailbox_stage.fxml"));
                        stage.setScene(new Scene(loader.load(), 1280, 720));
                        stage.getScene().getStylesheets().add(theme);
                        ResidentMailboxController mailboxPage = loader.getController();
                        mailboxPage.setTheme(theme);
                        mailboxPage.setAccountDataSource(accountDataSource);
                        mailboxPage.setAccounts(accounts);
                        mailboxPage.setRoomDataSource(roomDataSource);
                        mailboxPage.setRooms(rooms);
                        mailboxPage.setMailDataSource(mailDataSource);
                        mailboxPage.setMails(mails);
                        mailboxPage.setCurrentAccount(currentAccount.toResident());
                        stage.show();
                    } else if (currentAccount.isOfficer()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/room_manage_stage.fxml"));
                        stage.setScene(new Scene(loader.load(), 1280, 720));
                        stage.getScene().getStylesheets().add(theme);
                        RoomManageController roomManagePage = loader.getController();
                        roomManagePage.setTheme(theme);
                        roomManagePage.setAccounts(accounts);
                        roomManagePage.setAccountDataSource(accountDataSource);
                        roomManagePage.setRooms(rooms);
                        roomManagePage.setRoomDataSource(roomDataSource);
                        roomManagePage.setMails(mails);
                        roomManagePage.setMailDataSource(mailDataSource);
                        roomManagePage.setCurrentAccount(currentAccount.toOfficer());
                        stage.show();
                    } else if (currentAccount.isAdministrator()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/administratorMenu/administrator_stage.fxml"));
                        stage.setScene(new Scene(loader.load(), 1280, 720));
                        stage.getScene().getStylesheets().add(theme);
                        AdministratorController adminPage = loader.getController();
                        adminPage.setTheme(theme);
                        adminPage.setAccountDataSource(accountDataSource);
                        adminPage.setAccounts(accounts);
                        adminPage.setCurrentAccount(currentAccount.toAdministrator());
                        stage.show();
                    }
                } else {
                    if (currentAccount.isOfficer()) {
                        currentAccount.toOfficer().addBannedLogin();
                        accountDataSource.setAccountsData(accounts);
                    }
                    errorAlert.setText("This account has been disabled.");
                }
            } catch (Exception e) {
                errorAlert.setText("The Username or Password is incorrect.");
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
