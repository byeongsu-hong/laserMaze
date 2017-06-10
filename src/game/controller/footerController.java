package game.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 10..
 */
public class footerController implements Initializable {
    @FXML
    private HBox footer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button start = new Button("게임 시작");
        Button reset = new Button("게임 리셋");

        start.setOnMouseClicked(event -> {
            // 처리 알고리즘에 쏴주세욧
            System.out.println(event.getButton().toString() + "\n");
            System.out.println(event.getEventType().toString() + "\n");
        });
        reset.setOnMouseClicked(event -> {
            // 리셋시켜 주세욧
            System.out.println(event.getButton().toString() + "\n");
            System.out.println(event.getEventType().toString() + "\n");
        });

        footer.getChildren().addAll(start, reset);
    }
}
