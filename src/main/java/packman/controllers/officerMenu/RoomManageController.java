package packman.controllers.officerMenu;

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
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import packman.controllers.mainMenu.LoginController;
import packman.controllers.officerMenu.mailRoomMenu.CreateMailController;
import packman.controllers.officerMenu.mailRoomMenu.MailRoomController;
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
import java.util.ArrayList;

public class RoomManageController {
    private String theme;
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private RoomList rooms;
    private RoomDataSource roomDataSource;
    private MailList mails;
    private MailDataSource mailDataSource;
    private OfficerAccount currentAccount;

//    private String defaultAvatarPath = "/image/avatar.png";
    private String defaultAvatarPath = "image/default/avatar.png";
    private String defaultLabelText  = "EMPTY SLOT";
//    private String limitSignPath     = "/image/limit.png";
    private String limitSignPath     = "image/default/limit.png";
    private String limitLabelText    = "NOT AVAILABLE";

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setRooms(RoomList rooms) { this.rooms = rooms; }
    public void setRoomDataSource(RoomDataSource roomDataSource) { this.roomDataSource = roomDataSource; }
    public void setMails(MailList mails) { this.mails = mails; }
    public void setMailDataSource(MailDataSource mailDataSource) { this.mailDataSource = mailDataSource; }
    public void setCurrentAccount(OfficerAccount currentAccount) { this.currentAccount = currentAccount; }

    private Room selectedRoom;
    private ObservableList<Room> roomObservableList;
    private ArrayList<Account> roomers;

    @FXML Circle profileAvatar;
    @FXML TableView roomsTable;
    @FXML Label room_label;
    @FXML Label roomType_label;
    @FXML Circle roomer_1c; @FXML Circle roomer_2c; @FXML Circle roomer_3c; @FXML Circle roomer_4c;
    @FXML Label roomer_1l; @FXML Label roomer_2l; @FXML Label roomer_3l; @FXML Label roomer_4l;
    @FXML Button roomer_1b; @FXML Button roomer_2b; @FXML Button roomer_3b; @FXML Button roomer_4b;
    @FXML Label verifiedLabel; @FXML Button yesButton; @FXML Button noButton;
    @FXML Button residentRegisterButton; @FXML Button roomRegisterButton;
    @FXML Button logoutButton; @FXML Button changePasswordButton;
    @FXML TextField filterField;
    @FXML Button mailRoomButton;
    @FXML Pane pane_1;
    @FXML Pane pane_2;
    @FXML Pane pane_3;
    @FXML Pane pane_4;
    @FXML Label welcomeLabel;

    @FXML public void initialize() {
        hiddenGUI(false);
        Platform.runLater(() -> welcomeLabel.setText("Welcome! " + currentAccount.getFullName()));
        Platform.runLater(() -> profileAvatar.setFill(new ImagePattern(new Image(currentAccount.getPicPath()))));
        Platform.runLater(() -> defaultRoomersAvatar());
        Platform.runLater(() -> showRoomData());
        Platform.runLater(() -> roomsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedRoom((Room)newValue);
            }
        }));
    }

    @FXML public void hiddenGUI(boolean option) {
        verifiedLabel.setVisible(option); yesButton.setVisible(option); noButton.setVisible(option);
        profileAvatar.setVisible(!option); roomsTable.setVisible(!option); room_label.setVisible(!option); roomType_label.setVisible(!option);
        roomer_1c.setVisible(!option); roomer_2c.setVisible(!option); roomer_3c.setVisible(!option); roomer_4c.setVisible(!option);
        roomer_1l.setVisible(!option); roomer_2l.setVisible(!option); roomer_3l.setVisible(!option); roomer_4l.setVisible(!option);
        roomer_1b.setVisible(!option); roomer_2b.setVisible(!option); roomer_3b.setVisible(!option); roomer_4b.setVisible(!option);
        residentRegisterButton.setVisible(!option); roomRegisterButton.setVisible(!option); mailRoomButton.setVisible(!option);
        logoutButton.setVisible(!option); changePasswordButton.setVisible(!option); filterField.setVisible(!option);
        pane_1.setVisible(!option); pane_2.setVisible(!option); pane_3.setVisible(!option); pane_4.setVisible(!option);
        welcomeLabel.setVisible(!option);
    }

    @FXML private void searchRecord(KeyEvent ke) {
        FilteredList<Room> filteredData = new FilteredList<>(roomObservableList, p->true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pers -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toUpperCase();
                if (accounts.getRooms(typedText).size() == 0) {
                    return false;
                }
                for (Account account : accounts.getRooms(typedText)) {
                    if (account.toResident().getRoomID().equals(pers.getRoomID())) {
                        return true;
                    }
                }
                return false;
            });
            SortedList<Room> sortedList = new SortedList<>(filteredData);
            sortedList.comparatorProperty().bind(roomsTable.comparatorProperty());
            roomsTable.setItems(sortedList);
        } );
    }

    @FXML public void handleMailRoomButtonOnAction(ActionEvent event) throws IOException { mailRoomPage(event); }

    private void showRoomData() {
        rooms.toArrayList().sort(Room::compareTo);
        roomObservableList = FXCollections.observableArrayList(rooms.toArrayList());
        roomsTable.setItems(roomObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:RoomID", "field:roomID"));
        configs.add(new StringConfiguration("title:RoomType", "field:roomType"));
        configs.add(new StringConfiguration("title:Tower", "field:towerID"));
        configs.add(new StringConfiguration("title:FloorNumber", "field:floorNumber"));
        configs.add(new StringConfiguration("title:RoomOrder", "field:roomNumber"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            if (conf.get("title").equals("RoomID")) { col.setPrefWidth(65); }
            if (conf.get("title").equals("RoomType")) { col.setPrefWidth(80); }
            if (conf.get("title").equals("Tower")) { col.setPrefWidth(55); }
            if (conf.get("title").equals("FloorNumber")) { col.setPrefWidth(100); }
            if (conf.get("title").equals("RoomOrder")) { col.setPrefWidth(95); }
            col.setSortable(false);
            col.setResizable(false);
            roomsTable.getColumns().add(col);
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

    private void refreshAvatar() {
        defaultRoomersAvatar();
        setLimitSign();
        setRoomersAvatar();
    }

    private void showSelectedRoom(Room room) {
        selectedRoom = room;
        roomers = (ArrayList<Account>) accounts.getRoomers(selectedRoom.getRoomID());
        room_label.setText(selectedRoom.getRoomID());
        roomType_label.setText(selectedRoom.getRoomType());
        defaultRoomersAvatar();
        setLimitSign();
        setRoomersAvatar();
    }

    private void defaultRoomersAvatar() {
        roomer_1c.setFill(new ImagePattern(new Image(defaultAvatarPath))); roomer_2c.setFill(new ImagePattern(new Image(defaultAvatarPath)));
        roomer_3c.setFill(new ImagePattern(new Image(defaultAvatarPath))); roomer_4c.setFill(new ImagePattern(new Image(defaultAvatarPath)));
        roomer_1l.setText(defaultLabelText); roomer_2l.setText(defaultLabelText);
        roomer_3l.setText(defaultLabelText); roomer_4l.setText(defaultLabelText);
    }

    private void setLimitSign() {
        switch (rooms.getRoom(selectedRoom.getRoomID()).getMaxRoomer()) {
            case 1:
                roomer_2c.setFill(new ImagePattern(new Image(limitSignPath)));
                roomer_2l.setText(limitLabelText);
            case 2:
                roomer_3c.setFill(new ImagePattern(new Image(limitSignPath)));
                roomer_4c.setFill(new ImagePattern(new Image(limitSignPath)));
                roomer_3l.setText(limitLabelText);
                roomer_4l.setText(limitLabelText);
        }
    }

    private void setRoomersAvatar() {
        switch (roomers.size()) {
            case 4:
                roomer_4c.setFill(new ImagePattern(new Image(roomers.get(3).getPicPath())));
                roomer_4l.setText(roomers.get(3).getFullName());
            case 3:
                roomer_3c.setFill(new ImagePattern(new Image(roomers.get(2).getPicPath())));
                roomer_3l.setText(roomers.get(2).getFullName());
            case 2:
                roomer_2c.setFill(new ImagePattern(new Image(roomers.get(1).getPicPath())));
                roomer_2l.setText(roomers.get(1).getFullName());
            case 1:
                roomer_1c.setFill(new ImagePattern(new Image(roomers.get(0).getPicPath())));
                roomer_1l.setText(roomers.get(0).getFullName());
        }
    }

    @FXML public void handleRoomRegisterButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/create_room_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        CreateRoomController createRoomController = loader.getController();
        createRoomController.setTheme(theme);
        createRoomController.setAccountDataSource(accountDataSource);
        createRoomController.setAccounts(accounts);
        createRoomController.setRoomDataSource(roomDataSource);
        createRoomController.setRooms(rooms);
        createRoomController.setMailDataSource(mailDataSource);
        createRoomController.setMails(mails);
        createRoomController.setCurrentAccount(currentAccount);
        stage.show();
    }

    @FXML public void handleResidentRegisterButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/create_resident_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        CreateResidentController createResidentPage = loader.getController();
        createResidentPage.setTheme(theme);
        createResidentPage.setAccountDataSource(accountDataSource);
        createResidentPage.setAccounts(accounts);
        createResidentPage.setRoomDataSource(roomDataSource);
        createResidentPage.setRooms(rooms);
        createResidentPage.setMailDataSource(mailDataSource);
        createResidentPage.setMails(mails);
        createResidentPage.setCurrentAccount(currentAccount);
        stage.show();
    }

    @FXML public void handleChangePasswordButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/officer_change_pass_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        OfficerChangePassController officerChangePassPage = loader.getController();
        officerChangePassPage.setTheme(theme);
        officerChangePassPage.setAccountDataSource(accountDataSource);
        officerChangePassPage.setAccounts(accounts);
        officerChangePassPage.setRoomDataSource(roomDataSource);
        officerChangePassPage.setRooms(rooms);
        officerChangePassPage.setMailDataSource(mailDataSource);
        officerChangePassPage.setMails(mails);
        officerChangePassPage.setCurrentAccount(currentAccount);
        stage.show();
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

    @FXML public void handleRoomer_1bOnAction() { chooseRoomer(1); }
    @FXML public void handleRoomer_2bOnAction() { chooseRoomer(2); }
    @FXML public void handleRoomer_3bOnAction() { chooseRoomer(3); }
    @FXML public void handleRoomer_4bOnAction() { chooseRoomer(4); }

    private int deleteIndex;
    private void chooseRoomer(int index) {
        try {
            if (roomers.size() >= index) {
                deleteIndex = index;
                hiddenGUI(true);
            }
        } catch (Exception e) {
            // do nothing . . .
        }
    }

    @FXML public void handleYesButtonOnAction() {
        accounts.remove(roomers.get(deleteIndex-1));
        accountDataSource.setAccountsData(accounts);
        roomers.remove(deleteIndex-1);
        refreshAvatar();
        handleNoButtonOnAction();
    }

    @FXML public void handleNoButtonOnAction() {
        deleteIndex = 0;
        hiddenGUI(false);
    }
}
