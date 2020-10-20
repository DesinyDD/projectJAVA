package packman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Initial extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/mainMenu/login_stage.fxml"));
        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.setTitle("PackageManager");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.getScene().getStylesheets().add("/stylesheets/lightTheme.css");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
