package game.controller;

import game.app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 10..
 */
public class stageController implements Initializable {

    @FXML
    private StackPane stage;
    private final BorderPane wrapper;

    stageController(BorderPane wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader boardLoader = new FXMLLoader(app.getContext().getClass().getResource("view/board.fxml"));
        boardLoader.setController(new boardController(wrapper));
        GridPane board = null;

        try {
            board = boardLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Canvas canvas = new Canvas(250, 250);
        StackPane.setMargin(canvas, new Insets(-150, 130, 0, -10));
        GraphicsContext context = canvas.getGraphicsContext2D();

        stage.getChildren().addAll(canvas, board);
    }
}
