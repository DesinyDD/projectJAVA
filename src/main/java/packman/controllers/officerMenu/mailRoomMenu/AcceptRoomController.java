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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.buildings.RoomList;
import packman.models.mails.Mail;
import packman.models.mails.MailList;
import packman.services.StringConfiguration;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.roomDataBase.RoomDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class AcceptRoomController {
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

    private ObservableList<Mail> mailObservableList;
    private MailList mailBox = new MailList();

    @FXML private TextField filterField;
    @FXML private TableView mailsTable;
    @FXML private Button    backButton;

    @FXML public void initialize() {
        Platform.runLater(() -> mailBox.setMails(mails.getAcceptedMails()));
        Platform.runLater(() -> showMailData());
    }

    @FXML public void handleBackButtonOnAction(ActionEvent event) throws IOException { mailRoomPage(event); }

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

    private void mailRoomPage(ActionEvent event) throws IOException {
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
