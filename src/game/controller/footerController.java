package game.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 10..
 */
public class footerController implements Initializable {
    @FXML
    private HBox footer;
    private final GridPane board;

    footerController(GridPane board) {
        this.board = board;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        footer.setPadding(new Insets(0, 0, 50, 40));
        footer.setSpacing(20);

        Button start = new Button("게임 시작");
        start.setPrefWidth(100);

        Button reset = new Button("게임 리셋");
        reset.setPrefWidth(100);

        start.setOnMouseClicked(event -> {
            // 처리 알고리즘에 쏴주세욧
        });
        reset.setOnMouseClicked(event -> {
            // 리셋시켜 주세욧
            for(Node node: board.getChildren()) {
                VBox cell = (VBox)node;

                cell.getChildren().setAll();
            }
        });

        footer.getChildren().addAll(start, reset);
    }
}
