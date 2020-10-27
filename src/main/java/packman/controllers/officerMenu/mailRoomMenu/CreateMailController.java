package packman.controllers.officerMenu.mailRoomMenu;

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
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.buildings.RoomList;
import packman.models.mails.Mail;
import packman.models.mails.MailList;
import packman.models.mails.details.Size2D;
import packman.models.mails.details.Size3D;
import packman.models.mails.subtypes.Document;
import packman.models.mails.subtypes.Letter;
import packman.models.mails.subtypes.Parcel;
import packman.services.accountDataBase.AccountDataSource;
import packman.services.mailDataBase.MailDataSource;
import packman.services.roomDataBase.RoomDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateMailController {
    private String            theme;
    private AccountList       accounts;
    private AccountDataSource accountDataSource;
    private RoomList          rooms;
    private RoomDataSource    roomDataSource;
    private MailList          mails;
    private MailDataSource    mailDataSource;
    private OfficerAccount    currentAccount;

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }
    public void setAccounts(AccountList accounts) { this.accounts = accounts; }
    public void setAccountDataSource(AccountDataSource accountDataSource) { this.accountDataSource = accountDataSource; }
    public void setRooms(RoomList rooms) { this.rooms = rooms; }
    public void setRoomDataSource(RoomDataSource roomDataSource) { this.roomDataSource = roomDataSource; }
    public void setMails(MailList mails) { this.mails = mails; }
    public void setMailDataSource(MailDataSource mailDataSource) { this.mailDataSource = mailDataSource; }
    public void setCurrentAccount(OfficerAccount currentAccount) { this.currentAccount = currentAccount; }

    private String picturePath;

    @FXML private Button    backButton;
    @FXML private Label     errorLabel;
    @FXML private TextField trackingNumberField;
    @FXML private TextField courierBrandField;
    @FXML private TextField documentTypeField;
    @FXML private Button    chooserButton;
    @FXML private TextField mailTypeField;
    @FXML private TextField receiverFirstNameField;
    @FXML private TextField receiverLastNameField;
    @FXML private TextField receiverRoomIDField;
    @FXML private TextField picturePathField;
    @FXML private TextField senderFirstNameField;
    @FXML private TextField senderLastNameField;
    @FXML private TextField lengthField;
    @FXML private TextField widthField;
    @FXML private TextField heightField;
    @FXML private Circle    mailPicture;

    @FXML private Label documentLabel;
    @FXML private Label heightLabel;
    @FXML private Label trackingNumberLabel;
    @FXML private Label courierBrandLabel;

    @FXML public void initialize() {
//        mailPicture.setFill(new ImagePattern(new Image("image/mail.png")));
        mailPicture.setFill(new ImagePattern(new Image("image/default/mail.png")));
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
                        picturePathField.setText(selectedFile.getPath());
                        File destDir = new File("image/mailPicture");
                        destDir.mkdirs();
                        String[] fileSplit = selectedFile.getName().split("\\.");
                        String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "." + fileSplit[fileSplit.length - 1];
                        Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + File.separator + filename);
                        Files.copy(selectedFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                        mailPicture.setFill(new ImagePattern(new Image(target.toUri().toString())));

                        /* !!! SWAP IT BEFORE BUILD JAR FILE !!! */
                        picturePath = "image/mailPicture/" + target.getFileName().toString();      // ** FOR BUILD JAR FILE
//                        picturePath = target.toUri().toString();                                    // ** FOR IntelliJ IDEA
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML public void handleBackButtonOnAction(ActionEvent event) throws IOException { mailRoomPage(event); }

    @FXML public void handleRegisterMailButtonOnAction(ActionEvent event) throws IOException {
        String mailTypeInput = mailTypeField.getText().trim().toUpperCase();
        if (mailTypeInput.equals("LETTER") || mailTypeInput.equals("1")) {
            normalField();
            if (fillCheck() && checkRoomID() && checkLength() && checkWidth()) {
                createLetter();
                mailRoomPage(event);
            }
        } else if (mailTypeInput.equals("DOCUMENT") || mailTypeInput.equals("2")) {
            specialField(true);
            if (fillCheck() && specialFillCheck(true) && checkRoomID() && checkLength() && checkWidth()) {
                createDocument();
                mailRoomPage(event);
            }
        } else if (mailTypeInput.equals("PARCEL") || mailTypeInput.equals("3")) {
            specialField(false);
            if (fillCheck() && specialFillCheck(false) && checkRoomID() && checkLength() && checkWidth() && checkHeight()) {
                createParcel();
                mailRoomPage(event);
            }
        } else {
            if (mailTypeInput.isEmpty())
                errorLabel.setText("Please enter MailType.");
            else
                errorLabel.setText("MailType must be \"LETTER\", \"DOCUMENT\" or \"PARCEL\".");
            normalField();
        }
    }

    private void createLetter() {
        String receiverFirstName = receiverFirstNameField.getText().trim();
        String receiverLastName  = receiverLastNameField.getText().trim();
        String receiverRoomID    = receiverRoomIDField.getText().trim();
        String senderFirstName   = senderFirstNameField.getText().trim();
        String senderLastName    = senderLastNameField.getText().trim();
        double length            = Double.parseDouble(lengthField.getText().trim());
        double width             = Double.parseDouble(widthField.getText().trim());
        Mail newMail = new Letter(receiverFirstName, receiverLastName, receiverRoomID.toUpperCase(), senderFirstName, senderLastName,
                picturePath, currentAccount.getUsername(), LocalDateTime.now(), "",
                LocalDateTime.of(1,1,1,1,1,1), new Size2D(length, width));
        mails.add(newMail);
        mailDataSource.setMailsData(mails);
    }

    private void createDocument() {
        String receiverFirstName = receiverFirstNameField.getText().trim();
        String receiverLastName  = receiverLastNameField.getText().trim();
        String receiverRoomID    = receiverRoomIDField.getText().trim();
        String senderFirstName   = senderFirstNameField.getText().trim();
        String senderLastName    = senderLastNameField.getText().trim();
        double length            = Double.parseDouble(lengthField.getText().trim());
        double width             = Double.parseDouble(widthField.getText().trim());
        String documentType      = documentTypeField.getText().trim().toUpperCase();
        Mail newMail = new Document(receiverFirstName, receiverLastName, receiverRoomID.toUpperCase(), senderFirstName, senderLastName,
                picturePath, currentAccount.getUsername(), LocalDateTime.now(), "",
                LocalDateTime.of(1,1,1,1,1,1), new Size2D(length, width), documentType);
        mails.add(newMail);
        mailDataSource.setMailsData(mails);
    }

    private void createParcel() {
        String receiverFirstName = receiverFirstNameField.getText().trim();
        String receiverLastName  = receiverLastNameField.getText().trim();
        String receiverRoomID    = receiverRoomIDField.getText().trim();
        String senderFirstName   = senderFirstNameField.getText().trim();
        String senderLastName    = senderLastNameField.getText().trim();
        double length            = Double.parseDouble(lengthField.getText().trim());
        double width             = Double.parseDouble(widthField.getText().trim());
        double height            = Double.parseDouble(heightField.getText().trim());
        String trackingNumber    = trackingNumberField.getText().trim();
        String courierBrand      = courierBrandField.getText().trim();
        Mail newMail = new Parcel(receiverFirstName, receiverLastName, receiverRoomID.toUpperCase(), senderFirstName, senderLastName,
                picturePath, currentAccount.getUsername(), LocalDateTime.now(), "",
                LocalDateTime.of(1,1,1,1,1,1), new Size3D(length, width, height),
                courierBrand, trackingNumber);
        mails.add(newMail);
        mailDataSource.setMailsData(mails);
    }

    private boolean checkRoomID() {
        try {
            rooms.getRoom(receiverRoomIDField.getText().toUpperCase());
            return true;
        } catch (RuntimeException e) {
            errorLabel.setText("This Room ID is not found.");
            return false;
        }
    }

    private boolean checkLength() {
        try {
            String lengthInput = lengthField.getText().trim();
            double tempLength  = Double.parseDouble(lengthInput);
            if (tempLength <= 0) {
                errorLabel.setText("This Length must be greater than 0.");
                return false;
            } return true;
        } catch (RuntimeException e) {
            errorLabel.setText("Length must contain only digits.");
            return false;
        }
    }

    private boolean checkWidth() {
        try {
            String widthInput = widthField.getText().trim();
            double tempLength  = Double.parseDouble(widthInput);
            if (tempLength <= 0) {
                errorLabel.setText("This Width must be greater than 0.");
                return false;
            } return true;
        } catch (RuntimeException e) {
            errorLabel.setText("Width must contain only digits.");
            return false;
        }
    }

    private boolean checkHeight() {
        try {
            String heightInput = heightField.getText().trim();
            double tempHeight  = Double.parseDouble(heightInput);
            if (tempHeight <= 0) {
                errorLabel.setText("This Height must be greater than 0.");
                return false;
            } return true;
        } catch (RuntimeException e) {
            errorLabel.setText("Height must contain only digits.");
            return false;
        }
    }

    private void normalField() {
        documentLabel.setVisible(false);
        documentTypeField.setVisible(false);
        trackingNumberLabel.setVisible(false);
        trackingNumberField.setVisible(false);
        courierBrandLabel.setVisible(false);
        courierBrandField.setVisible(false);
        heightLabel.setVisible(false);
        heightField.setVisible(false);
    }

    private void specialField(boolean key) {
        documentLabel.setVisible(key);
        documentTypeField.setVisible(key);
        trackingNumberLabel.setVisible(!key);
        trackingNumberField.setVisible(!key);
        courierBrandLabel.setVisible(!key);
        courierBrandField.setVisible(!key);
        heightLabel.setVisible(!key);
        heightField.setVisible(!key);
    }

    private boolean fillCheck() {
        String receiverFirstNameInput = receiverFirstNameField.getText().trim();
        String receiverLastNameInput  = receiverLastNameField.getText().trim();
        String receiverRoomIDInput    = receiverRoomIDField.getText().trim();
        String picturePathInput       = picturePathField.getText().trim();
        String senderFirstNameInput   = senderFirstNameField.getText().trim();
        String senderLastNameInput    = senderLastNameField.getText().trim();
        String lengthInput            = lengthField.getText().trim();
        String widthInput             = widthField.getText().trim();
        if (receiverFirstNameInput.isEmpty()) {
            errorLabel.setText("Please enter Receiver's First Name.");
            return false;
        } else if (receiverLastNameInput.isEmpty()) {
            errorLabel.setText("Please enter Receiver's Last Name.");
            return false;
        } else if (receiverRoomIDInput.isEmpty()) {
            errorLabel.setText("Please enter Receiver's Room ID.");
            return false;
        } else if (senderFirstNameInput.isEmpty()) {
            errorLabel.setText("Please enter Sender's First Name.");
            return false;
        } else if (senderLastNameInput.isEmpty()) {
            errorLabel.setText("Please enter Sender's Last Name.");
            return false;
        } else if (lengthInput.isEmpty()) {
            errorLabel.setText("Please enter Mail's Length.");
            return false;
        } else if (widthInput.isEmpty()) {
            errorLabel.setText("Please enter Mail's Width.");
            return false;
        } else if (picturePathInput.isEmpty()) {
            errorLabel.setText("Please upload Mail's Picture.");
            return false;
        } return true;
    }

    private boolean specialFillCheck(boolean key) {
        String documentTypeInput   = documentTypeField.getText().trim().toUpperCase();
        String trackingNumberInput = trackingNumberField.getText().trim();
        String courierBrandInput   = courierBrandField.getText().trim();
        String heightInput         = heightField.getText().trim();
        if (key) {
            if (documentTypeInput.isEmpty()) {
                errorLabel.setText("Please enter Document Type.");
                return false;
            } return true;
        } else {
            if (heightInput.isEmpty()) {
                errorLabel.setText("Please enter Mail's Height.");
                return false;
            } else if (courierBrandInput.isEmpty()) {
                errorLabel.setText("Please enter Courier Brand.");
                return false;
            } else if (trackingNumberInput.isEmpty()) {
                errorLabel.setText("Please enter Tracking Number.");
                return false;
            } return true;
        }
    }

    private void mailRoomPage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stages/officerMenu/mailRoomMenu/mail_room_stage.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
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