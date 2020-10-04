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
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import packman.models.account.Account;
import packman.models.account.AccountList;
import packman.models.account.accountSubtype.ResidentAccount;
import packman.models.account.details.Name;
import packman.models.account.details.Picture;
import packman.models.building.RoomList;
import packman.models.building.details.RoomType;
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
    @FXML private TextField roomNumberField;
    @FXML private TextField fileField;
    @FXML private Circle profileView;
    @FXML private Button chooserButton;
    @FXML private Button registerButton;
    @FXML private Button homeButton;
    @FXML private Label errorLabel;

    @FXML public void initialize() {
        profileView.setFill(new ImagePattern(new Image("images/default/defaultPic.png")));
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
                        File destDir = new File("src/main/resources/images");
                        destDir.mkdirs();
                        String[] fileSplit = selectedFile.getName().split("\\.");
                        String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "." + fileSplit[fileSplit.length - 1];
                        Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + File.separator + filename);
                        Files.copy(selectedFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                        profileView.setFill(new ImagePattern(new Image(target.toUri().toString())));
                        picturePath = target.toUri().toString();
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

        /* Error Report for Empty Field */
        if (firstnameField.getText().isEmpty()) { errorLabel.setText("Please enter first name."); }
        else if (lastnameField.getText().isEmpty()) { errorLabel.setText("Please enter last name."); }
        else if (usernameField.getText().isEmpty()) { errorLabel.setText("Please enter username."); }
        else if (roomNumberField.getText().isEmpty()) { errorLabel.setText("Please enter room number."); }
        else if (passwordField.getText().isEmpty() || reEnterPasswordField.getText().isEmpty()) { errorLabel.setText("Please enter password."); }
        else if (fileField.getText().isEmpty()) { errorLabel.setText("Please upload a profile picture."); }

        /* Others Report */
        else if (accounts.isUsed(usernameField.getText())) { errorLabel.setText("This username is already in use. Try again."); }
        else if (!passwordField.getText().equals(reEnterPasswordField.getText())) { errorLabel.setText("Those passwords didn't match. Try again."); }
        else if (!rooms.isAvailable(roomNumberField.getText())) { errorLabel.setText("This room number is not available. Try again."); }
        else if (rooms.findByRoomNumber(roomNumberField.getText()).getRoomType().equals(RoomType.STUDIO) && accounts.totalRoomer(roomNumberField.getText()) == 1) { errorLabel.setText("This room number is full."); }
        else if (rooms.findByRoomNumber(roomNumberField.getText()).getRoomType().equals(RoomType.DUPLEX) && accounts.totalRoomer(roomNumberField.getText()) == 2) { errorLabel.setText("This room number is full."); }

        /* Create Resident Account & Back to login menu */
        else {
            Account newAccount = new ResidentAccount(new Picture(picturePath), LocalDateTime.now(), usernameField.getText(), passwordField.getText(), new Name(firstnameField.getText(), lastnameField.getText()), roomNumberField.getText());
            accounts.add(newAccount);
            accountDataSource.setAccountsData(accounts);

            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/mainMenu/login_stage.fxml"));
            stage.setScene(new Scene(loader.load(),1280,720));
            stage.getScene().getStylesheets().add(theme);
            LoginController loginPage = loader.getController();
            loginPage.setAccountDataSource(accountDataSource);
            loginPage.setAccounts(accounts);
            loginPage.setTheme(theme);
            stage.show();
        }
    }
}
