package game.controller.card;

import game.app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class CardWrapper implements Initializable {
    @FXML
    private VBox cardWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader menuWrapperLoader = new FXMLLoader(app.getResource("view/card/MenuWrapper.fxml"));
        FXMLLoader stageWrapperLoader = new FXMLLoader(app.getResource("view/card/StageWrapper.fxml"));
        FXMLLoader launchWrapperLoader = new FXMLLoader(app.getResource("view/card/LaunchWrapper.fxml"));

        try {
            BorderPane menuWrapper = menuWrapperLoader.load();
            StackPane stageWrapper = stageWrapperLoader.load();
            BorderPane launchWrapper = launchWrapperLoader.load();

            cardWrapper.getChildren().setAll(menuWrapper, stageWrapper, launchWrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
