package game.controller;

import game.app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class MainWrapper implements Initializable {

    @FXML
    private BorderPane mainWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader cardWrapperLoader = new FXMLLoader(app.getResource("view/card/CardWrapper.fxml"));
        FXMLLoader menuWrapperLoader = new FXMLLoader(app.getResource("view/menu/MenuWrapper.fxml"));

        try {
            VBox cardWrapper = cardWrapperLoader.load();
            GridPane menuWrapper = menuWrapperLoader.load();

            BorderPane.setAlignment(cardWrapper, Pos.CENTER);
            BorderPane.setAlignment(menuWrapper, Pos.CENTER);

            mainWrapper.setLeft(cardWrapper);
            mainWrapper.setRight(menuWrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
