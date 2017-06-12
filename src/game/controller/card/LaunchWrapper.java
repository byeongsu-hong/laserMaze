package game.controller.card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class LaunchWrapper implements Initializable {
    @FXML
    private BorderPane launchWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button start = new Button("Game start");
        Button reset = new Button("Game Reset");

        start.setPrefWidth(150);
        reset.setPrefWidth(150);

        launchWrapper.setLeft(start);
        launchWrapper.setRight(reset);
    }
}
