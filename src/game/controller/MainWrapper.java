package game.controller;

import game.app;
import game.controller.card.CardWrapper;
import game.controller.menu.MenuWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
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

    public static final int TOKEN_WIDTH = 48;
    public static final int TOKEN_HEIGHT = 48;

    @FXML
    private BorderPane mainWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader cardWrapperLoader = new FXMLLoader(app.getResource("view/card/CardWrapper.fxml"));
        FXMLLoader menuWrapperLoader = new FXMLLoader(app.getResource("view/menu/MenuWrapper.fxml"));

        try {
            cardWrapperLoader.setController(new CardWrapper());
            VBox cardWrapper = cardWrapperLoader.load();

            mainWrapper.setLeft(cardWrapper);

            ImageView cursorImage = new ImageView();
            mainWrapper.getChildren().add(cursorImage);

            menuWrapperLoader.setController(new MenuWrapper(mainWrapper));
            GridPane menuWrapper = menuWrapperLoader.load();

            mainWrapper.setRight(menuWrapper);

            BorderPane.setAlignment(cardWrapper, Pos.CENTER);
            BorderPane.setAlignment(menuWrapper, Pos.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
