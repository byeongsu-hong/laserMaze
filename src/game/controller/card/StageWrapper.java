package game.controller.card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static game.controller.MainWrapper.TOKEN_HEIGHT;
import static game.controller.MainWrapper.TOKEN_WIDTH;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class StageWrapper implements Initializable {
    @FXML
    private StackPane stageWrapper;

    private final VBox card;

    public StageWrapper(VBox card) {
        this.card = card;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GridPane board = new GridPane();
        board.setStyle("-fx-background-color: aliceblue");

        for(int i = 0; i < 5; i++ ){
            for(int j = 0; j < 5; j++){
                VBox cell = new VBox();
                cell.setPrefWidth(TOKEN_WIDTH + 2);
                cell.setPrefHeight(TOKEN_HEIGHT + 2);
                cell.setStyle("-fx-border-color: #333;");
                board.add(cell, i, j);
            }
        }

        Canvas canvas = new Canvas(250, 250);
        stageWrapper.getChildren().setAll(canvas, board);
    }
}
