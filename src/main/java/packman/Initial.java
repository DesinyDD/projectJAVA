package packman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Initial extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/login_stage.fxml"));
        primaryStage.setTitle("PackageManager");
        // primaryStage.initStyle(StageStyle.TRANSPARENT); // SetBorderless
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getScene()
                    .getStylesheets()
                    .add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
