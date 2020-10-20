package packman.controllers.administratorMenu;

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
import packman.models.accounts.subtypes.AdministratorAccount;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.services.accountDataBase.AccountDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OfficerRegisterController {
    private String theme;
    private AccountList accounts;
    private AccountDataSource accountDataSource;
    private AdministratorAccount currentAccount;
    private String picturePath;

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setCurrentAccount(AdministratorAccount currentAccount) { this.currentAccount = currentAccount; }

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField reEnterPasswordField;
    @FXML private TextField fileField;
    @FXML private Circle profileView;
    @FXML private Button chooserButton;
    @FXML private Button registerButton;
    @FXML private Button homeButton;
    @FXML private Label errorLabel;

    @FXML public void initialize() {
        profileView.setFill(new ImagePattern(new Image("/images/avatar.png")));
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
//                        File destDir = new File("src/main/resources/images/staffAvatar");
                        File destDir = new File("image/staffAvatar");
                        destDir.mkdirs();
                        String[] fileSplit = selectedFile.getName().split("\\.");
                        String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "." + fileSplit[fileSplit.length - 1];
                        Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + File.separator + filename);
                        Files.copy(selectedFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                        profileView.setFill(new ImagePattern(new Image(target.toUri().toString())));
//                        picturePath = "/images/staffAvatar/" + target.getFileName().toString();
                        picturePath = "image/staffAvatar/" + target.getFileName().toString();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/administratorMenu/administrator_stage.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        stage.getScene().getStylesheets().add(theme);
        AdministratorController adminPage = loader.getController();
        adminPage.setTheme(theme);
        adminPage.setAccountDataSource(accountDataSource);
        adminPage.setAccounts(accounts);
        adminPage.setCurrentAccount(currentAccount);
        stage.show();
    }

    @FXML public void handleRegisterButtonOnAction(ActionEvent event) throws IOException {

        /* Error Report for Empty Field */
        if (firstNameField.getText().isEmpty()) { errorLabel.setText("Please enter first name."); }
        else if (lastNameField.getText().isEmpty()) { errorLabel.setText("Please enter last name."); }
        else if (usernameField.getText().isEmpty()) { errorLabel.setText("Please enter username."); }
        else if (passwordField.getText().isEmpty() || reEnterPasswordField.getText().isEmpty()) { errorLabel.setText("Please enter password."); }
        else if (fileField.getText().isEmpty()) { errorLabel.setText("Please upload a profile picture."); }

        /* Others Report */
        else if (accounts.isUsernameUsed(usernameField.getText())) { errorLabel.setText("This username is already in use. Try again."); }
        else if (!passwordField.getText().equals(reEnterPasswordField.getText())) { errorLabel.setText("Those passwords didn't match. Try again."); }

        /* Create Resident Account & Back to login menu */
        else {
            Account newAccount = new OfficerAccount(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText(), picturePath, LocalDateTime.of(1,1,1,1,1,1), false, 0);
            accounts.add(newAccount);
            accountDataSource.setAccountsData(accounts);
            this.handleHomeButtonOnAction(event);
        }
    }
}
