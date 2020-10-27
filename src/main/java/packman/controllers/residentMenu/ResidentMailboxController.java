package packman.controllers.residentMenu;

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
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import packman.controllers.mainMenu.LoginController;
import packman.models.accounts.Account;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.ResidentAccount;
import packman.models.buildings.RoomList;
import packman.models.mails.Mail;
import packman.models.mails.MailList;
import packman.services.StringConfiguration;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.roomDataBase.RoomDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class ResidentMailboxController {
    private String            theme;
    private AccountList       accounts;
    private AccountDataSource accountDataSource;
    private RoomList          rooms;
    private RoomDataSource    roomDataSource;
    private MailList          mails;
    private MailDataSource    mailDataSource;
    private ResidentAccount   currentAccount;

    private Mail selectedMail;
    private ObservableList<Mail> mailObservableList;
    private MailList mailBox;

    private String defaultMailPath = "image/default/mail.png";
//    private String defaultMailPath = "/image/mail.png";

    /* Setter */
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setRooms(RoomList rooms) { this.rooms = rooms; }
    public void setRoomDataSource(RoomDataSource roomDataSource) { this.roomDataSource = roomDataSource; }
    public void setMails(MailList mails) { this.mails = mails; }
    public void setMailDataSource(MailDataSource mailDataSource) { this.mailDataSource = mailDataSource; }
    public void setCurrentAccount(ResidentAccount account) { this.currentAccount = account; }
    public void setTheme(String theme) { this.theme = theme; }

    @FXML private TableView<Mail> mailsTable;

    /* PANE #01 */
    @FXML private Circle currentAccountAvatar;
    @FXML private Label  currentAccountUsername;
    @FXML private Label  currentAccountFirstName;
    @FXML private Label  currentAccountLastName;
    @FXML private Label  currentAccountRoomID;
    @FXML private Button changePasswordButton;
    @FXML private Button logoutButton;

    /* PANE #02 */
    @FXML private Circle mailPicture;
    @FXML private Label description_1;
    @FXML private Label description_2;

    @FXML public void initialize() {
        Platform.runLater(() -> currentAccountAvatar.setFill(new ImagePattern(new Image(currentAccount.getPicPath()))));
        Platform.runLater(() -> currentAccountUsername.setText(currentAccount.getUsername()));
        Platform.runLater(() -> currentAccountFirstName.setText(currentAccount.getFirstName()));
        Platform.runLater(() -> currentAccountLastName.setText(currentAccount.getLastName()));
        Platform.runLater(() -> currentAccountRoomID.setText(currentAccount.getRoomID()));

        Platform.runLater(() -> mailPicture.setFill(new ImagePattern(new Image(defaultMailPath))));

        mailBox = new MailList();
        Platform.runLater(() -> mailBox.setMails(mails.getMails(currentAccount.getRoomID())));
        Platform.runLater(() -> showMailData());

        mailsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedMail(newValue);
            }
        });
    }

    @FXML public void handleChangePasswordButtonOnAction(ActionEvent event) throws IOException { changePasswordPage(event); }

    @FXML public void handleLogoutButtonOnAction(ActionEvent event) throws IOException { loginPage(event); }

    private void changePasswordPage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/residentMenu/change_password_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        ChangePasswordController changePasswordPage = loader.getController();
        changePasswordPage.setTheme(theme);
        changePasswordPage.setAccounts(accounts);
        changePasswordPage.setAccountDataSource(accountDataSource);
        changePasswordPage.setRooms(rooms);
        changePasswordPage.setRoomDataSource(roomDataSource);
        changePasswordPage.setMails(mails);
        changePasswordPage.setMailDataSource(mailDataSource);
        changePasswordPage.setCurrentAccount(currentAccount);
        stage.show();
    }

    private void loginPage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/login_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        LoginController loginPage = loader.getController();
        loginPage.setTheme(theme);
        stage.show();
    }

    private void showMailData() {
        mailBox.toArrayList().sort(Mail::compareTo);
        mailObservableList = FXCollections.observableArrayList(mailBox.toArrayList());
        mailsTable.setItems(mailObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:RoomID", "field:receiverRoomID"));
        configs.add(new StringConfiguration("title:Receiver", "field:receiverName"));
        configs.add(new StringConfiguration("title:Sender", "field:senderName"));
        configs.add(new StringConfiguration("title:Size", "field:SizeFormat"));
        configs.add(new StringConfiguration("title:CheckIn", "field:checkIn"));
        configs.add(new StringConfiguration("title:CheckInOfficer", "field:checkInOfficer"));
        configs.add(new StringConfiguration("title:CheckOut", "field:checkOut"));
        configs.add(new StringConfiguration("title:CheckOutOfficer", "field:checkOutOfficer"));
        configs.add(new StringConfiguration("title:MailType", "field:type"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            if (conf.get("title").equals("RoomID")) { col.setPrefWidth(55); }
            if (conf.get("title").equals("Receiver")) { col.setPrefWidth(160); }
            if (conf.get("title").equals("Sender")) { col.setPrefWidth(160); }
            if (conf.get("title").equals("Size")) { col.setPrefWidth(120); }
            if (conf.get("title").equals("CheckIn")) { col.setPrefWidth(130); }
            if (conf.get("title").equals("CheckInOfficer")) { col.setPrefWidth(100); }
            if (conf.get("title").equals("CheckOut")) { col.setPrefWidth(130); }
            if (conf.get("title").equals("CheckOutOfficer")) { col.setPrefWidth(100); }
            if (conf.get("title").equals("MailType")) { col.setPrefWidth(70); }
            col.setSortable(false);
            col.setResizable(false);
            mailsTable.getColumns().add(col);
        }
    }

    private void showSelectedMail(Mail mail) {
        selectedMail = mail;
        description_1.setText("");
        description_2.setText("");
        mailPicture.setFill(new ImagePattern(new Image(selectedMail.getPicPath())));
        if (selectedMail.isParcel()) {
            description_1.setText(String.format("CourierBrand : %s", selectedMail.toParcel().getCourierBrand()));
            description_2.setText(String.format("TrackingNumber : %s", selectedMail.toParcel().getTrackingNumber()));
        } else if (selectedMail.isDocument())
            description_1.setText(String.format("DocumentType : %s", selectedMail.toDocument().getDocumentType()));
    }
}
