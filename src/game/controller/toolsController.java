package game.controller;

import game.app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 10..
 */
public class toolsController implements Initializable {
    @FXML
    private VBox tools;

    private final BorderPane wrapper;

    toolsController(BorderPane wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tools.setAlignment(Pos.CENTER);

        FXMLLoader addToGridLoader = new FXMLLoader(app.getContext().getClass().getResource("view/addToGrid.fxml"));
        FXMLLoader tokenLoader = new FXMLLoader(app.getContext().getClass().getResource("view/token.fxml"));

        try {
            addToGridLoader.setController(new addToGridController(wrapper));
            tools.getChildren().add(addToGridLoader.load());

            tokenLoader.setController(new tokenController(wrapper, tools));
            tools.getChildren().add(tokenLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
