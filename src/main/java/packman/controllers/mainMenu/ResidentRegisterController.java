package packman.controllers.mainMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import packman.models.accounts.Account;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.ResidentAccount;
import packman.models.buildings.RoomList;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.roomDataBase.RoomDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ResidentRegisterController {
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private RoomList rooms;
    private RoomDataSource roomDataSource;
    private String theme;
    private String picturePath;

    /* Setter */
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setRooms(RoomList rooms) { this.rooms = rooms; }
    public void setRoomDataSource(RoomDataSource roomDataSource) { this.roomDataSource = roomDataSource; }
    public void setTheme(String theme) { this.theme = theme; }

    @FXML private TextField firstnameField;
    @FXML private TextField lastnameField;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField reEnterPasswordField;
    @FXML private TextField roomIDField;
    @FXML private TextField fileField;
    @FXML private Circle profileView;
    @FXML private Button chooserButton;
    @FXML private Button registerButton;
    @FXML private Button homeButton;
    @FXML private Label errorLabel;

    @FXML public void initialize() {
//        profileView.setFill(new ImagePattern(new Image("image/avatar.png")));
        profileView.setFill(new ImagePattern(new Image("image/default/avatar.png")));
        chooserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.PNG", "*.JPG"));
                File selectedFile = chooser.showOpenDialog(chooserButton.getScene().getWindow());
                if (selectedFile != null) {
                    try {
                        picturePath = selectedFile.toURI().toString();
                        fileField.setText(selectedFile.getPath());
                        File destDir = new File("image/userAvatar");
                        destDir.mkdirs();
                        String[] fileSplit = selectedFile.getName().split("\\.");
                        String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "." + fileSplit[fileSplit.length - 1];
                        Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + File.separator + filename);
                        Files.copy(selectedFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                        profileView.setFill(new ImagePattern(new Image(target.toUri().toString())));

                        /* !!! SWAP IT BEFORE BUILD JAR FILE !!! */
                        picturePath = "image/userAvatar/" + target.getFileName().toString();      // ** FOR BUILD JAR FILE
//                        picturePath = target.toUri().toString();                                    // ** FOR IntelliJ IDEA
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML public void handleHomeButtonOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/login_stage.fxml"));
        stage.setScene(new Scene(loader.load(),1280,720));
        stage.getScene().getStylesheets().add(theme);
        LoginController loginPage = loader.getController();
        loginPage.setTheme(theme);
        stage.show();
    }

    @FXML public void handleRegisterButtonOnAction(ActionEvent event) throws IOException {
        if (firstnameField.getText().isEmpty()) { errorLabel.setText("Please enter First Name."); }
        else if (lastnameField.getText().isEmpty()) { errorLabel.setText("Please enter Last Name."); }
        else if (usernameField.getText().isEmpty()) { errorLabel.setText("Please enter Username."); }
        else if (accounts.isUsernameUsed(usernameField.getText())) { errorLabel.setText("This Username is already in use. Try again."); }
        else if (roomIDField.getText().isEmpty()) { errorLabel.setText("Please enter Room ID."); }
        else if (!rooms.isAvailable(roomIDField.getText())) { errorLabel.setText("This Room ID is not available. Try again."); }
        else if (rooms.getRoom(roomIDField.getText()).isStudio() && accounts.totalRoomer(roomIDField.getText()) == 1) { errorLabel.setText("This room number is full."); }
        else if (rooms.getRoom(roomIDField.getText()).isSuite()  && accounts.totalRoomer(roomIDField.getText()) == 2) { errorLabel.setText("This room number is full."); }
        else if (rooms.getRoom(roomIDField.getText()).isDuplex() && accounts.totalRoomer(roomIDField.getText()) == 4) { errorLabel.setText("This room number is full."); }
        else if (passwordField.getText().isEmpty() || reEnterPasswordField.getText().isEmpty()) { errorLabel.setText("Please enter Password."); }
        else if (!passwordField.getText().equals(reEnterPasswordField.getText())) { errorLabel.setText("Those Passwords didn't match. Try again."); }
        else if (fileField.getText().isEmpty()) { errorLabel.setText("Please upload a Profile Picture."); }
        else {
            Account newAccount = new ResidentAccount(usernameField.getText(), passwordField.getText(), firstnameField.getText(), lastnameField.getText(), picturePath, LocalDateTime.now(), true, roomIDField.getText(), LocalDateTime.now());
            accounts.add(newAccount);
            accountDataSource.setAccountsData(accounts);
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/login_stage.fxml"));
            stage.setScene(new Scene(loader.load(),1280,720));
            stage.getScene().getStylesheets().add(theme);
            LoginController loginPage = loader.getController();
            loginPage.setTheme(theme);
            loginPage.setAccountDataSource(accountDataSource);
            loginPage.setAccounts(accounts);
            stage.show();
        }
    }
}
