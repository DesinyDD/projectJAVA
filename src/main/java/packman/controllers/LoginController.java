package packman.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML TextField userField;
    @FXML PasswordField passwordField;
    @FXML Button loginButton;

//    @FXML public void initialize() { }

    @FXML public void handleLoginButtonOnAction(ActionEvent event) {
        System.out.println(passwordField.getText());
    }
}
