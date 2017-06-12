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

    private static app context;
    public app() {
        context = this;
    }

    public static URL getResource(String path) {
        return context.getClass().getResource(path);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadFonts();

        Parent root = FXMLLoader.load(getClass().getResource("view/MainWrapper.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    private void loadFonts() {
        Font.loadFont(getClass().getResource("res/fonts/Roboto-Black.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-BlackItalic.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-Bold.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-BoldItalic.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-Italic.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-Light.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-LightItalic.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-Medium.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-MediumItalic.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-Thin.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("res/fonts/Roboto-ThinItalic.ttf").toExternalForm(), 10);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
