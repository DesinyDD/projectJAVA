package packman.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import packman.models.account.Account;

import java.io.File;

public class ResidentMailboxController {
    private Account currentAccount;
    private String theme;

    /* Setter */
    public void setCurrentAccount(Account account) { this.currentAccount = account; }
    public void setTheme(String theme) { this.theme = theme; }

    @FXML private ImageView imageView;
    @FXML private Label testLabel;
    @FXML public void initialize() {
        Platform.runLater(() -> testLabel.setText(currentAccount.getImagePath()));
        Platform.runLater(() -> imageView.setImage(currentAccount.getImage()));
    }

}
