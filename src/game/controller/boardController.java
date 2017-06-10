package game.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by hongbyeongsu on 2017. 6. 9..
 */
public class boardController implements Initializable {
    @FXML
    private GridPane board;
    private final BorderPane wrapper;

    public boardController(BorderPane wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                VBox cell = new VBox();
                cell.setStyle("" +
                        "-fx-max-width: 50px;" +
                        "-fx-min-width: 50px;" +
                        "-fx-max-height: 50px;" +
                        "-fx-min-height: 50px;" +
                        "-fx-border-color: #333;" +
                        "-fx-border-width: 1;");

                board.add(cell, i, j);
            }
        }
    }

    static VBox getCellByColRow(GridPane board, int col, int row) {
        for(Node node: board.getChildren()) {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (VBox)node;
            }
        }

        return null;
    }
}
