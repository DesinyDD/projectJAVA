package packman.controllers.mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class DeveloperController {
    private String theme;

    /* Setter */
    public void setTheme(String theme) { this.theme = theme; }

    @FXML private Button homeButton;
    @FXML private Circle profileAvatar;

    @FXML public void initialize() {
        profileAvatar.setFill(new ImagePattern(new Image("image/default/myPicture.jpg")));
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
}
