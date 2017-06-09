package game;

import game.controller.mainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by hongbyeongsu on 2017. 6. 9..
 */
public class app extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        root.setStyle("" +
                "-fx-padding: 20px;");

        try {
            root.setTop(getHeader());
            root.setRight(getItemMenu());
            root.setCenter(getBoard());
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("laserMaze");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    private VBox getHeader() {
        VBox head = new VBox();

        Text text = new Text("Laser Maze");
        head.setStyle("" +
                "-fx-font-size: 25;" +
                "-fx-text-alignment: center;");

        head.getChildren().add(text);

        return head;
    }

    private GridPane getBoard() throws IOException {
        GridPane board = FXMLLoader.load(getClass().getResource("view/board.fxml"));
        board.setStyle("" +
                "-fx-padding: 20px;");

        return board;
    }

    private GridPane getItemMenu() throws IOException {
        GridPane itemMenu = FXMLLoader.load(getClass().getResource("view/itemMenu.fxml"));
        itemMenu.setStyle("" +
                "-fx-padding: 20px;");

        return itemMenu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
