package game.controller.card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class StageWrapper implements Initializable {
    @FXML
    private StackPane stageWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GridPane board = new GridPane();

        for(int i = 0; i < 5; i++ ){
            for(int j = 0; j < 5; j++){
                VBox cell = new VBox();
                cell.setPrefWidth(50);
                cell.setPrefHeight(50);
                cell.setStyle("-fx-border-color: #333;");
                board.add(cell, i, j);
            }
        }

        Canvas canvas = new Canvas(250, 250);
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.setFill(Color.ALICEBLUE);
        context.fillRect(0, 0, 250, 250);

        stageWrapper.getChildren().setAll(canvas, board);
    }
}
