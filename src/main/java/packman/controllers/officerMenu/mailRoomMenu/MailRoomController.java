package packman.controllers.officerMenu.mailRoomMenu;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import packman.controllers.mainMenu.LoginController;
import packman.controllers.officerMenu.RoomManageController;
import packman.models.accounts.Account;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.buildings.Room;
import packman.models.buildings.RoomList;
import packman.models.mails.Mail;
import packman.models.mails.MailList;
import packman.services.StringConfiguration;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.roomDataBase.RoomDataSource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MailRoomController {
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

    private Mail selectedMail;
    private ObservableList<Mail> mailObservableList;
    private MailList mailBox = new MailList();

    private String defaultMailPath = "image/default/mail.png";
//    private String defaultMailPath = "/image/mail.png";

    private FilteredList filteredData;

    @FXML private Circle mailPicture;
    @FXML private Circle currentAccountAvatar;
    @FXML private TableView mailsTable;
    @FXML private Label mailLabel;
    @FXML private Label description_1;
    @FXML private Label description_2;
    @FXML private Button backButton;
    @FXML private TextField filterField;

    @FXML public void initialize() {
        Platform.runLater(() -> currentAccountAvatar.setFill(new ImagePattern(new Image(currentAccount.getPicPath()))));
        Platform.runLater(() -> mailPicture.setFill(new ImagePattern(new Image(defaultMailPath))));
        Platform.runLater(() -> mailBox.setMails(mails.getDisMissedMails()));
        Platform.runLater(() -> showMailData());
        Platform.runLater(() -> mailsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedMail((Mail)newValue);
            }
        }));
    }

    @FXML private void searchRecord(KeyEvent ke) {
        FilteredList<Mail> filteredData = new FilteredList<>(mailObservableList, p->true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pers -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toUpperCase();
                if (pers.getReceiverRoomID().toUpperCase().indexOf(typedText) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<Mail> sortedList = new SortedList<>(filteredData);
            sortedList.comparatorProperty().bind(mailsTable.comparatorProperty());
            mailsTable.setItems(sortedList);
        } );
    }

    @FXML public void handleShowCheckOutButton(ActionEvent event) throws IOException { acceptRoomPage(event); }

    @FXML public void handleCheckOutButtonOInAction(ActionEvent event) throws IOException {
        if (selectedMail != null) {
            selectedMail.setSendingTime(LocalDateTime.now());
            selectedMail.setSendingOfficerUsername(currentAccount.getUsername());
            mailDataSource.setMailsData(mails);
            refreshPage(event);
        }
    }

    @FXML public void handleAddMailButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/mailRoomMenu/create_mail_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        CreateMailController createMailPage = loader.getController();
        createMailPage.setTheme(theme);
        createMailPage.setAccountDataSource(accountDataSource);
        createMailPage.setAccounts(accounts);
        createMailPage.setRoomDataSource(roomDataSource);
        createMailPage.setRooms(rooms);
        createMailPage.setMailDataSource(mailDataSource);
        createMailPage.setMails(mails);
        createMailPage.setCurrentAccount(currentAccount);
        stage.show();
    }

    private void showSelectedMail(Mail mail) {
        selectedMail = mail;
        description_1.setText("");
        description_2.setText("");
        mailPicture.setFill(new ImagePattern(new Image(selectedMail.getPicPath())));
        mailLabel.setText(selectedMail.getType());
        if (selectedMail.isParcel()) {
            description_1.setText(String.format("CourierBrand : %s", selectedMail.toParcel().getCourierBrand()));
            description_2.setText(String.format("TrackingNumber : %s", selectedMail.toParcel().getTrackingNumber()));
        } else if (selectedMail.isDocument())
            description_1.setText(String.format("DocumentType : %s", selectedMail.toDocument().getDocumentType()));
    }

    private void showMailData() {
        mailBox.toArrayList().sort(Mail::compareTo);
        mailObservableList = FXCollections.observableArrayList(mailBox.toArrayList());
        mailsTable.setItems(mailObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:MailType", "field:type"));
        configs.add(new StringConfiguration("title:RoomID", "field:receiverRoomID"));
        configs.add(new StringConfiguration("title:Receiver", "field:receiverName"));
        configs.add(new StringConfiguration("title:Sender", "field:senderName"));
        configs.add(new StringConfiguration("title:CheckIn", "field:checkIn"));
        configs.add(new StringConfiguration("title:CheckInOfficer", "field:checkInOfficer"));
        configs.add(new StringConfiguration("title:CheckOut", "field:checkOut"));
        configs.add(new StringConfiguration("title:CheckOutOfficer", "field:checkOutOfficer"));
        configs.add(new StringConfiguration("title:Size", "field:SizeFormat"));
        configs.add(new StringConfiguration("title:Length", "field:length"));
        configs.add(new StringConfiguration("title:Width", "field:width"));
        configs.add(new StringConfiguration("title:Height", "field:height"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            if (conf.get("title").equals("RoomID")) { col.setSortable(true); }
            else if (conf.get("title").equals("CheckIn")) { col.setPrefWidth(120); col.setSortable(true); }
            else if (conf.get("title").equals("Receiver")) { col.setPrefWidth(150); col.setSortable(false); }
            else if (conf.get("title").equals("Sender")) { col.setPrefWidth(150); col.setSortable(false); }
            else if (conf.get("title").equals("CheckInOfficer")) { col.setPrefWidth(110); col.setSortable(false); }
            else if (conf.get("title").equals("CheckOutOfficer")) { col.setPrefWidth(110); col.setSortable(false); }
            else if (conf.get("title").equals("CheckOut")) { col.setPrefWidth(120); col.setSortable(false); }
            else if (conf.get("title").equals("Size")) { col.setPrefWidth(140); col.setSortable(false); }
            else if (conf.get("title").equals("Length")) { col.setPrefWidth(60); col.setSortable(false); }
            else if (conf.get("title").equals("Width")) { col.setPrefWidth(60); col.setSortable(false); }
            else if (conf.get("title").equals("Height")) { col.setPrefWidth(60); col.setSortable(false); }
            else { col.setSortable(false); }
            col.setResizable(false);
            mailsTable.getColumns().add(col);
        }
    }

    @FXML public void handleBackButtonOnAction(ActionEvent event) throws IOException { roomManagePage(event); }

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

    private void acceptRoomPage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/mailRoomMenu/accept_room_stage.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        stage.getScene().getStylesheets().add(theme);
        AcceptRoomController acceptRoomPage = loader.getController();
        acceptRoomPage.setTheme(theme);
        acceptRoomPage.setAccountDataSource(accountDataSource);
        acceptRoomPage.setAccounts(accounts);
        acceptRoomPage.setRoomDataSource(roomDataSource);
        acceptRoomPage.setRooms(rooms);
        acceptRoomPage.setMailDataSource(mailDataSource);
        acceptRoomPage.setMails(mails);
        acceptRoomPage.setCurrentAccount(currentAccount);
        stage.show();
    }

    private void refreshPage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/mailRoomMenu/mail_room_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        MailRoomController mailRoomPage = loader.getController();
        mailRoomPage.setTheme(theme);
        mailRoomPage.setAccountDataSource(accountDataSource);
        mailRoomPage.setAccounts(accounts);
        mailRoomPage.setRoomDataSource(roomDataSource);
        mailRoomPage.setRooms(rooms);
        mailRoomPage.setMailDataSource(mailDataSource);
        mailRoomPage.setMails(mails);
        mailRoomPage.setCurrentAccount(currentAccount);
        stage.show();
    }


}
