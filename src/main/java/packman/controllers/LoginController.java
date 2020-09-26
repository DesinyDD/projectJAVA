package packman.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField userField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

//    @FXML public void initialize() { }

    @FXML public void handleLoginButtonOnAction(ActionEvent event) {
        System.out.println(passwordField.getText());
    }
}
