package packman.controllers.residentMenu;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import java.util.ArrayList;

public class ResidentMailboxController {
    private String theme;
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private RoomList rooms;
    private RoomDataSource roomDataSource;
    private MailList mails;
    private MailDataSource mailDataSource;
    private ResidentAccount currentAccount;

    private Mail selectedMail;
    private ObservableList<Mail> mailObservableList;
    private MailList mailsInThisRoom;

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
    @FXML private ImageView imageView;
    @FXML private Label testLabel;


    @FXML public void initialize() {
        Platform.runLater(() -> imageView.setImage(new Image(currentAccount.getPicPath())));
//        Platform.runLater(() -> testLabel.setText(currentAccount.getImagePath()));

        mailsInThisRoom = new MailList();
        Platform.runLater(() -> mailsInThisRoom.setMails(mailDataSource.getMailsData().getMails(((ResidentAccount)currentAccount).getRoomID())));
        Platform.runLater(() -> showMailData());

        mailsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
//                showSelectedStudent(newValue);
            }
        });
    }

    private void showMailData() {
        mailObservableList = FXCollections.observableArrayList(mailsInThisRoom.toArrayList());
        mailsTable.setItems(mailObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:MailType", "field:mailType"));
        configs.add(new StringConfiguration("title:Name", "field:receiverName"));
        configs.add(new StringConfiguration("title:Sender", "field:senderName"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            mailsTable.getColumns().add(col);
        }
    }

    private void showSelectedStudent(Mail mail) {
        selectedMail = mail;
//        sidLabel.setText(mail.getId());
//        nameLabel.setText(mail.getName());
//        assignmentScoreTextField.setText(String.valueOf(mail.getScore().get(ScoreType.ASSIGNMENT)));
//        attendanceScoreTextField.setText(String.valueOf(mail.getScore().get(ScoreType.ATTENDANCE)));
//        midtermScoreTextField.setText(String.valueOf(mail.getScore().get(ScoreType.MIDTERM_EXAM)));
//        finalScoreTextField.setText(String.valueOf(mail.getScore().get(ScoreType.FINAL_EXAM)));
//        updateScoreButton.setDisable(false);
    }

}
