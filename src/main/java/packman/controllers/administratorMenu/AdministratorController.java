package packman.controllers.administratorMenu;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import packman.controllers.mainMenu.LoginController;
import packman.models.accounts.Account;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.AdministratorAccount;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.services.StringConfiguration;
import packman.services.accountDataBase.AccountDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class AdministratorController {
    private String theme;
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private AdministratorAccount currentAccount;

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setCurrentAccount(AdministratorAccount currentAccount) { this.currentAccount = currentAccount; }

    private OfficerAccount selectedOfficer;
    private ObservableList<Account> officerObservableList;
    private AccountList officers;

    @FXML private TableView officersTable;
    @FXML private Circle officerAvatar;
    @FXML private Circle profileAvatar;
    @FXML private Button logoutButton;
    @FXML private Label banStatus;

    @FXML public void initialize() {
        Platform.runLater(() -> officerAvatar.setFill(new ImagePattern(new Image("/images/avatar.png"))));
        Platform.runLater(() -> profileAvatar.setFill(new ImagePattern(new Image(currentAccount.getPicPath()))));
        Platform.runLater(() -> officers = new AccountList());
        Platform.runLater(() -> officers.setAccounts(accounts.getOfficers()));
        Platform.runLater(() -> showOfficerData());
        Platform.runLater(() -> officersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedOfficer((OfficerAccount) newValue);
            }
        }));
    }

    private void showOfficerData() {
        officerObservableList = FXCollections.observableArrayList(officers.toArrayList());
        officersTable.setItems(officerObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:BanStatus", "field:banStatus"));
        configs.add(new StringConfiguration("title:Username", "field:username"));
        configs.add(new StringConfiguration("title:Password", "field:password"));
        configs.add(new StringConfiguration("title:FirstName", "field:firstName"));
        configs.add(new StringConfiguration("title:LastName", "field:lastName"));
        configs.add(new StringConfiguration("title:LastLogin", "field:lastLoginString"));
        configs.add(new StringConfiguration("title:BannedLogin", "field:bannedLoginCount"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            officersTable.getColumns().add(col);
        }
    }

    @FXML public void handleLogoutButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/login_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        LoginController loginPage = loader.getController();
        loginPage.setTheme(theme);
        stage.show();
    }

    @FXML public void handleBanButtonOnAction() {
        if (selectedOfficer != null) {
            selectedOfficer.switchBan();
            accountDataSource.setAccountsData(accounts);
            officersTable.refresh();
            showSelectedOfficer(selectedOfficer);
        }
    }

    private void showSelectedOfficer(OfficerAccount officer) {
        selectedOfficer = officer;
        officerAvatar.setFill(new ImagePattern(new Image(officer.getPicPath())));
        if (officer.isBanned()) {
            banStatus.setText("BANNED");
        } else { banStatus.setText("NOT-BAN"); }
    }

    @FXML public void handleChangePasswordOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/administratorMenu/admin_change_pass_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        AdminChangePassController changeAdminPassPage = loader.getController();
        changeAdminPassPage.setTheme(theme);
        changeAdminPassPage.setAccountDataSource(accountDataSource);
        changeAdminPassPage.setAccounts(accounts);
        changeAdminPassPage.setCurrentAccount(currentAccount);
        stage.show();
    }

    @FXML public void handleCreateAccountButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/administratorMenu/officer_register_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        OfficerRegisterController registerOfficerPage = loader.getController();
        registerOfficerPage.setTheme(theme);
        registerOfficerPage.setAccountDataSource(accountDataSource);
        registerOfficerPage.setAccounts(accounts);
        registerOfficerPage.setCurrentAccount(currentAccount);
        stage.show();
    }
}
