package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class app extends Application {

    public static int WIDTH = 900;
    public static int HEIGHT = 600;
    public static String IMAGE_LOCATION = "old_images";

    private static app context;
    public app() {
        context = this;
    }

    public static URL getResource(String path) {
        return context.getClass().getResource(path);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/MainWrapper.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
